package windows;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPopupMenu;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
		setBounds(100, 100, 330, 150);
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
		labelAddOrder.setBounds(30, 75, 100, 10);
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
				ViewOrderHistoryWindow vohw = new ViewOrderHistoryWindow(userId);
				vohw.setVisible(true);
			}
		});
		btnLabelViewOrders.setBounds(120, 25, 48, 48);
		contentPane.add(btnLabelViewOrders);

		JLabel labelViewOrders = new JLabel("Order History");
		labelViewOrders.setBounds(115, 75, 100, 10);
		contentPane.add(labelViewOrders);

		BufferedImage buttonCalculateTime = null;
		try {
			buttonCalculateTime = ImageIO.read(classLoader.getResourceAsStream("Time-48.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel btnCalculateTime = new JLabel(new ImageIcon(buttonCalculateTime));
		btnCalculateTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BakeTimeDialog btd = new BakeTimeDialog();
				btd.setVisible(true);
				btd.addCancelListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						btd.dispose();
					}
				});
			}
		});
		btnCalculateTime.setBounds(210, 25, 48, 48);
		contentPane.add(btnCalculateTime);

		JLabel labelCalculateTime = new JLabel("Calculate bake time");
		labelCalculateTime.setBounds(200, 75, 100, 10);
		contentPane.add(labelCalculateTime);

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
