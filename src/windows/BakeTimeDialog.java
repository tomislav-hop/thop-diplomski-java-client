package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gsonObjects.Item;
import implementations.ItemImpl;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BakeTimeDialog extends JDialog {

	private static final long serialVersionUID = -3301238436301114083L;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxItem;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtWeight;
	private JTextField txtBakeTime;
	private List<Item> itemList;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BakeTimeDialog dialog = new BakeTimeDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public BakeTimeDialog() {
		setBounds(100, 100, 279, 185);
		getContentPane().setLayout(new BorderLayout());

		setTitle("Calculate bake time");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Time-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(10, 11, 46, 14);
		contentPanel.add(lblItem);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(10, 36, 46, 14);
		contentPanel.add(lblWeight);

		JLabel lblBakeTime = new JLabel("Bake time: ");
		lblBakeTime.setBounds(10, 61, 53, 14);
		contentPanel.add(lblBakeTime);

		comboBoxItem = new JComboBox();
		comboBoxItem.setBounds(92, 8, 160, 20);
		loadDataForComboBoxes();

		txtWeight = new JTextField();
		txtWeight.setBounds(92, 33, 160, 20);
		contentPanel.add(txtWeight);
		txtWeight.setColumns(10);

		txtBakeTime = new JTextField();
		txtBakeTime.setEditable(false);
		txtBakeTime.setBounds(92, 58, 160, 20);
		contentPanel.add(txtBakeTime);
		txtBakeTime.setColumns(10);

		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(10, 86, 242, 23);

		btnCalculate.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				ItemImpl ii = new ItemImpl();
				int itemId = ii.getItemId(itemList, comboBoxItem.getSelectedItem().toString());
				txtBakeTime.setText(ii.getBakeTime(itemId, txtWeight.getText()));
				txtBakeTime.setForeground(Color.BLUE);
				txtBakeTime.setBackground(Color.YELLOW);
			}
		});

		contentPanel.add(btnCalculate);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Close");
				cancelButton.setActionCommand("Close");
				buttonPane.add(cancelButton);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void loadDataForComboBoxes() {
		ItemImpl ii = new ItemImpl();
		itemList = ii.getItemList();

		String[] comboItem = new String[itemList.size()];
		int counter = 0;
		for (Item i : itemList) {
			comboItem[counter] = i.getItemName();
			counter++;
		}
		DefaultComboBoxModel<String> cbmItem = new DefaultComboBoxModel<>(comboItem);
		contentPanel.setLayout(null);
		comboBoxItem.setModel(cbmItem);
		contentPanel.add(comboBoxItem);
	}

	public void addCancelListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}
}
