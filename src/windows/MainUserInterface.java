package windows;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPopupMenu;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class MainUserInterface extends JFrame {

	private static final long serialVersionUID = 619541736836981888L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainUserInterface(int userId) {
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Deliver Food-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(classLoader.getResourceAsStream("Dining Room-48.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel btnLabel = new JLabel(new ImageIcon(buttonIcon));
		btnLabel.setBackground(Color.LIGHT_GRAY);
		btnLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddOrderWindow aow = new AddOrderWindow(userId);
				aow.setVisible(true);
			}
		});
		btnLabel.setBounds(30, 25, 48, 48);
		contentPane.add(btnLabel);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
