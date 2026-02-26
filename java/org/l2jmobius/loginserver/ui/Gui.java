package org.l2jmobius.loginserver.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.l2jmobius.commons.config.InterfaceConfig;
import org.l2jmobius.commons.ui.DarkTheme;
import org.l2jmobius.commons.ui.LineLimitListener;
import org.l2jmobius.commons.ui.SplashScreen;
import org.l2jmobius.loginserver.GameServerTable;
import org.l2jmobius.loginserver.LoginController;
import org.l2jmobius.loginserver.LoginServer;

public class Gui
{
	private static final String[] SHUTDOWN_OPTIONS = new String[]
	{
		"Shutdown",
		"Cancel"
	};
	private static final String[] RESTART_OPTIONS = new String[]
	{
		"Restart",
		"Cancel"
	};
	private final JTextArea _txtrConsole;
	private final JCheckBoxMenuItem _chckbxmntmEnabled;
	private JCheckBoxMenuItem _chckbxmntmDisabled;
	private JCheckBoxMenuItem _chckbxmntmGmOnly;
	
	public Gui()
	{
		System.setProperty("sun.java2d.opengl", "false");
		System.setProperty("sun.java2d.d3d", "false");
		System.setProperty("sun.java2d.noddraw", "true");
		if (InterfaceConfig.DARK_THEME)
		{
			DarkTheme.activate();
		}
		
		this._txtrConsole = new JTextArea();
		this._txtrConsole.setEditable(false);
		this._txtrConsole.setLineWrap(true);
		this._txtrConsole.setWrapStyleWord(true);
		this._txtrConsole.setDropMode(DropMode.INSERT);
		this._txtrConsole.setFont(new Font("Monospaced", 0, 16));
		this._txtrConsole.getDocument().addDocumentListener(new LineLimitListener(500));
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", 0, 14));
		JMenu mnActions = new JMenu("Actions");
		mnActions.setFont(new Font("Segoe UI", 0, 13));
		menuBar.add(mnActions);
		JMenuItem mntmShutdown = new JMenuItem("Shutdown");
		mntmShutdown.setFont(new Font("Segoe UI", 0, 13));
		mntmShutdown.addActionListener(_ -> {
			if (JOptionPane.showOptionDialog(null, "Shutdown LoginServer?", "Select an option", 0, 3, null, SHUTDOWN_OPTIONS, SHUTDOWN_OPTIONS[1]) == 0)
			{
				LoginServer.getInstance().shutdown(false);
			}
		});
		mnActions.add(mntmShutdown);
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mntmRestart.setFont(new Font("Segoe UI", 0, 13));
		mntmRestart.addActionListener(_ -> {
			if (JOptionPane.showOptionDialog(null, "Restart LoginServer?", "Select an option", 0, 3, null, RESTART_OPTIONS, RESTART_OPTIONS[1]) == 0)
			{
				LoginServer.getInstance().shutdown(true);
			}
		});
		mnActions.add(mntmRestart);
		JMenu mnReload = new JMenu("Reload");
		mnReload.setFont(new Font("Segoe UI", 0, 13));
		menuBar.add(mnReload);
		JMenuItem mntmBannedIps = new JMenuItem("Banned IPs");
		mntmBannedIps.setFont(new Font("Segoe UI", 0, 13));
		mntmBannedIps.addActionListener(_ -> {
			LoginController.getInstance().getBannedIps().clear();
			LoginServer.getInstance().loadBanFile();
		});
		mnReload.add(mntmBannedIps);
		JMenu mnStatus = new JMenu("Status");
		mnStatus.setFont(new Font("Segoe UI", 0, 13));
		menuBar.add(mnStatus);
		this._chckbxmntmEnabled = new JCheckBoxMenuItem("Enabled");
		this._chckbxmntmEnabled.setFont(new Font("Segoe UI", 0, 13));
		this._chckbxmntmEnabled.addActionListener(_ -> {
			this._chckbxmntmEnabled.setSelected(true);
			this._chckbxmntmDisabled.setSelected(false);
			this._chckbxmntmGmOnly.setSelected(false);
			LoginServer.getInstance().setStatus(2);
			
			for (GameServerTable.GameServerInfo gsi : GameServerTable.getInstance().getRegisteredGameServers().values())
			{
				gsi.setStatus(2);
			}
			
			LoginServer.LOGGER.info("Status changed to enabled.");
		});
		this._chckbxmntmEnabled.setSelected(true);
		mnStatus.add(this._chckbxmntmEnabled);
		this._chckbxmntmDisabled = new JCheckBoxMenuItem("Disabled");
		this._chckbxmntmDisabled.setFont(new Font("Segoe UI", 0, 13));
		this._chckbxmntmDisabled.addActionListener(_ -> {
			this._chckbxmntmEnabled.setSelected(false);
			this._chckbxmntmDisabled.setSelected(true);
			this._chckbxmntmGmOnly.setSelected(false);
			LoginServer.getInstance().setStatus(4);
			
			for (GameServerTable.GameServerInfo gsi : GameServerTable.getInstance().getRegisteredGameServers().values())
			{
				gsi.setStatus(4);
			}
			
			LoginServer.LOGGER.info("Status changed to disabled.");
		});
		mnStatus.add(this._chckbxmntmDisabled);
		this._chckbxmntmGmOnly = new JCheckBoxMenuItem("GM only");
		this._chckbxmntmGmOnly.setFont(new Font("Segoe UI", 0, 13));
		this._chckbxmntmGmOnly.addActionListener(_ -> {
			this._chckbxmntmEnabled.setSelected(false);
			this._chckbxmntmDisabled.setSelected(false);
			this._chckbxmntmGmOnly.setSelected(true);
			LoginServer.getInstance().setStatus(5);
			
			for (GameServerTable.GameServerInfo gsi : GameServerTable.getInstance().getRegisteredGameServers().values())
			{
				gsi.setStatus(5);
			}
			
			LoginServer.LOGGER.info("Status changed to GM only.");
		});
		mnStatus.add(this._chckbxmntmGmOnly);
		JMenu mnFont = new JMenu("Font");
		mnFont.setFont(new Font("Segoe UI", 0, 13));
		menuBar.add(mnFont);
		String[] fonts = new String[]
		{
			"16",
			"21",
			"27",
			"33"
		};
		
		for (String font : fonts)
		{
			JMenuItem mntmFont = new JMenuItem(font);
			mntmFont.setFont(new Font("Segoe UI", 0, 13));
			mntmFont.addActionListener(_ -> this._txtrConsole.setFont(new Font("Monospaced", 0, Integer.parseInt(font))));
			mnFont.add(mntmFont);
		}
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", 0, 13));
		menuBar.add(mnHelp);
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(new Font("Segoe UI", 0, 13));
		mntmAbout.addActionListener(_ -> new frmAbout());
		mnHelp.add(mntmAbout);
		List<Image> icons = new ArrayList<>();
		icons.add(new ImageIcon(".." + File.separator + "images" + File.separator + "16x16.png").getImage());
		icons.add(new ImageIcon(".." + File.separator + "images" + File.separator + "32x32.png").getImage());
		final JScrollPane scrollPanel = new JScrollPane(this._txtrConsole);
		scrollPanel.setBounds(0, 0, 800, 550);
		final JFrame frame = new JFrame("LoginServer");
		frame.setDefaultCloseOperation(0);
		frame.addWindowListener(new WindowAdapter()
		{
			{
				Objects.requireNonNull(Gui.this);
			}
			
			@Override
			public void windowClosing(WindowEvent ev)
			{
				if (JOptionPane.showOptionDialog(null, "Shutdown LoginServer?", "Select an option", 0, 3, null, Gui.SHUTDOWN_OPTIONS, Gui.SHUTDOWN_OPTIONS[1]) == 0)
				{
					LoginServer.getInstance().shutdown(false);
				}
			}
		});
		frame.addComponentListener(new ComponentAdapter()
		{
			{
				Objects.requireNonNull(Gui.this);
			}
			
			@Override
			public void componentResized(ComponentEvent ev)
			{
				scrollPanel.setSize(frame.getContentPane().getSize());
			}
		});
		frame.setJMenuBar(menuBar);
		frame.setIconImages(icons);
		frame.add(scrollPanel, "Center");
		frame.getContentPane().setPreferredSize(new Dimension(InterfaceConfig.DARK_THEME ? 815 : 800, 550));
		frame.pack();
		frame.setLocationRelativeTo(null);
		this.redirectSystemStreams();
		new SplashScreen(".." + File.separator + "images" + File.separator + "splashscreen.gif", 100L, frame);
	}
	
	void updateTextArea(String text)
	{
		SwingUtilities.invokeLater(() -> {
			this._txtrConsole.append(text);
			this._txtrConsole.setCaretPosition(this._txtrConsole.getText().length());
		});
	}
	
	private void redirectSystemStreams()
	{
		OutputStream out = new OutputStream()
		{
			{
				Objects.requireNonNull(Gui.this);
			}
			
			@Override
			public void write(int b)
			{
				Gui.this.updateTextArea(String.valueOf((char) b));
			}
			
			@Override
			public void write(byte[] b, int off, int len)
			{
				Gui.this.updateTextArea(new String(b, off, len));
			}
			
			@Override
			public void write(byte[] b)
			{
				this.write(b, 0, b.length);
			}
		};
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
	}
}
