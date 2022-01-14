package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JTextPane;

public class Result extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Result(String s) {
		setTitle("Result");
		setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int) dimension.getWidth() / 2 - 155, (int) dimension.getHeight() / 2 - 107, 310, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblRes = new JLabel(s);
			lblRes.setHorizontalAlignment(SwingConstants.CENTER);
			if (s.equals("Operation finished successfully!"))
				lblRes.setForeground(Color.GREEN);
			else
				lblRes.setForeground(Color.RED);
			contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
			contentPanel.add(lblRes);
		}
		{
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JEditorPane dtrpnVersion = new JEditorPane();
					dtrpnVersion.setEditable(false);
					dtrpnVersion.setContentType("text/html");
					dtrpnVersion.setText("<b>v1.0 - 01/03/2021 see in:</b>");
					panel.add(dtrpnVersion);
				}
				JEditorPane dtrpnName = new JEditorPane();
				panel.add(dtrpnName);
				dtrpnName.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent arg0) {
						browse("https://www.mpgh.net/forum/forumdisplay.php?f=175");
					}
				});
				dtrpnName.setFont(new Font("Tahoma", Font.PLAIN, 11));
				dtrpnName.setEditable(false);
				dtrpnName.setContentType("text/html");
				dtrpnName.setText("<b><a href=''>Here</a></b>");
			}
		}
		{
			JTextPane txtpnDonateAndHelp = new JTextPane();
			txtpnDonateAndHelp.setContentType("text/html");
			txtpnDonateAndHelp.setEditable(false);
			txtpnDonateAndHelp.setText(
					"<center>Donate and help software stay free and updated!\r\n(Pst... any amount is just fine)</center>");
			contentPanel.add(txtpnDonateAndHelp);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						terminate();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnDonate = new JButton("Donate");
				btnDonate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						browse("https://pastebin.com/1t3uDVpR");
					}
				});
				buttonPane.add(btnDonate);
			}
		}
	}

	private void browse(String url) { // Windows only
		try {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(new URI(url));
			} else {
				Runtime rt = Runtime.getRuntime();
				rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private void terminate() {
		this.dispose();
	}
}
