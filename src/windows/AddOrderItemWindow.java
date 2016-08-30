package windows;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DateTimePicker;

import gsonObjects.Item;
import gsonObjects.Package;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

@SuppressWarnings("rawtypes")
public class AddOrderItemWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5890971577745119529L;
	
	JComboBox comboBoxPackage;
	JComboBox comboBoxItem;
	private JPanel contentPane;
	private JTextField txtWeight;
	private JTextField txtAmount;
	private DateTimePicker deadline;
	private DateTimePicker deliveryTime;
	private List<Item> itemList;
	private List<Package> packageList;

	/**
	 * Create the frame.
	 */
	public AddOrderItemWindow(List<Item> itemList, List<Package> packageList) {
		this.itemList = itemList;
		this.packageList = packageList;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxItem = new JComboBox();
		comboBoxItem.setBounds(98, 14, 341, 20);
		
		comboBoxPackage = new JComboBox();
		comboBoxPackage.setBounds(98, 141, 341, 20);
		
		loadDataForComboBoxes();

		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(10, 14, 46, 14);
		contentPane.add(lblItem);

		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(10, 47, 46, 14);
		contentPane.add(lblDeadline);

		deadline = new DateTimePicker();
		deadline.setBounds(98, 47, 341, 20);
		contentPane.add(deadline);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(10, 84, 46, 14);
		contentPane.add(lblWeight);

		txtWeight = new JTextField();
		txtWeight.setBounds(98, 84, 115, 20);
		contentPane.add(txtWeight);
		txtWeight.setColumns(10);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(268, 87, 46, 14);
		contentPane.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setBounds(324, 84, 115, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);

		JCheckBox chckbxDelivery = new JCheckBox("Delivery");
		chckbxDelivery.setBounds(98, 111, 97, 23);
		contentPane.add(chckbxDelivery);

		JCheckBox chckbxCool = new JCheckBox("Cool");
		chckbxCool.setBounds(209, 111, 97, 23);
		contentPane.add(chckbxCool);

		JCheckBox chckbxCut = new JCheckBox("Cut");
		chckbxCut.setBounds(324, 111, 97, 23);
		contentPane.add(chckbxCut);

		JLabel lblPackage = new JLabel("Package");
		lblPackage.setBounds(10, 141, 46, 14);
		contentPane.add(lblPackage);

		JLabel lblAdditionalNotes = new JLabel("Additional notes:");
		lblAdditionalNotes.setBounds(10, 166, 81, 14);
		contentPane.add(lblAdditionalNotes);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(98, 172, 341, 78);
		contentPane.add(textArea);

		JLabel lblDeliveryTime = new JLabel("Delivery time:");
		lblDeliveryTime.setBounds(10, 264, 81, 14);
		contentPane.add(lblDeliveryTime);

		deliveryTime = new DateTimePicker();
		deliveryTime.setBounds(98, 261, 341, 20);
		contentPane.add(deliveryTime);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 293, 429, 46);
		contentPane.add(btnAdd);
	}

	@SuppressWarnings("unchecked")
	private void loadDataForComboBoxes() {
		String[] comboItem = new String[itemList.size()];
		int counter = 0;
		for (Item i : itemList) {
			comboItem[counter] = i.getItemName() + "  " + i.getItemDescription();
			counter++;
		}
		DefaultComboBoxModel<String> cbmItem = new DefaultComboBoxModel<>(comboItem);
		comboBoxItem.setModel(cbmItem);
		contentPane.add(comboBoxItem);

		String[] comboPackage = new String[packageList.size()];
		counter = 0;
		for (Package p : packageList) {
			comboPackage[counter] = p.getPackageName();
			counter++;
		}
		DefaultComboBoxModel<String> cbmPackage = new DefaultComboBoxModel<>(comboPackage);
		comboBoxPackage.setModel(cbmPackage);
		contentPane.add(comboBoxPackage);
	}
}
