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
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Deliver Food-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setTitle("Main Window");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BufferedImage buttonIconAddOrder = null;
		try {
			buttonIconAddOrder = ImageIO.read(classLoader.getResourceAsStream("Dining Room-48.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel btnLabelAddOrder = new JLabel(new ImageIcon(buttonIconAddOrder));
		btnLabelAddOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddOrderWindow aow = new AddOrderWindow(userId);
				aow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				aow.setVisible(true);
			}
		});
		btnLabelAddOrder.setBounds(30, 25, 48, 48);
		contentPane.add(btnLabelAddOrder);
		
		JLabel labelAddOrder = new JLabel("Add Order");
		labelAddOrder.setBounds(30, 75,100, 10);
		contentPane.add(labelAddOrder);
		
		BufferedImage buttonIconViewOrders = null;
		try {
			buttonIconViewOrders = ImageIO.read(classLoader.getResourceAsStream("Clock-48.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel btnLabelViewOrders = new JLabel(new ImageIcon(buttonIconViewOrders));
		btnLabelViewOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: Show order history
			}
		});
		btnLabelViewOrders.setBounds(120, 25, 48, 48);
		contentPane.add(btnLabelViewOrders);
		
		JLabel labelViewOrders = new JLabel("Order History");
		labelViewOrders.setBounds(115, 75, 100, 10);
		contentPane.add(labelViewOrders);
		
		
		
		
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
