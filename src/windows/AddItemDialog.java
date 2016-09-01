package windows;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DateTimePicker;

import gsonObjects.Item;
import gsonObjects.Package;
import implementations.DateTimePickerParser;

@SuppressWarnings("rawtypes")
public class AddItemDialog extends JDialog {

	private static final long serialVersionUID = -221239550539803236L;
	private final JPanel contentPanel = new JPanel();
	JComboBox comboBoxPackage;
	JComboBox comboBoxItem;
	private JTextField txtWeight;
	private JTextField txtAmount;
	private DateTimePicker deadline;
	private DateTimePicker deliveryTime;
	private List<Item> itemList;
	private List<Package> packageList;
	private JButton okButton;
	private JButton cancelButton;
	private JTextArea textArea;
	private JCheckBox chckbxDelivery;
	private JCheckBox chckbxCool;
	private JCheckBox chckbxCut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddItemDialog dialog = new AddItemDialog(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddItemDialog(List<Item> itemList, List<Package> packageList) {
		this.itemList = itemList;
		this.packageList = packageList;
		setBounds(100, 100, 468, 370);
		getContentPane().setLayout(null);
		setTitle("Add Item To Order");
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Dining Room-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPanel.setBounds(0, 0, 448, 296);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);

		comboBoxItem = new JComboBox();
		comboBoxItem.setBounds(90, 11, 347, 20);
		comboBoxItem.setFont(new Font("SansSerif", Font.PLAIN, 12));

		comboBoxPackage = new JComboBox();
		comboBoxPackage.setBounds(90, 121, 347, 20);
		comboBoxPackage.setFont(new Font("SansSerif", Font.PLAIN, 12));

		loadDataForComboBoxes();

		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(10, 14, 30, 14);
		lblItem.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblItem);

		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setBounds(10, 39, 55, 14);
		lblDeadline.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblDeadline);

		JLabel lblWeight = new JLabel("Weight:");
		lblWeight.setBounds(10, 67, 45, 14);
		lblWeight.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblWeight);

		txtWeight = new JTextField();
		txtWeight.setBounds(90, 64, 120, 20);
		txtWeight.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(txtWeight);
		txtWeight.setColumns(10);

		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(253, 67, 50, 14);
		lblAmount.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblAmount);

		txtAmount = new JTextField();
		txtAmount.setBounds(317, 64, 120, 20);
		txtAmount.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(txtAmount);
		txtAmount.setColumns(10);

		chckbxDelivery = new JCheckBox("Delivery");
		chckbxDelivery.setBounds(90, 91, 80, 23);
		chckbxDelivery.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(chckbxDelivery);

		chckbxCool = new JCheckBox("Cool");
		chckbxCool.setBounds(187, 91, 60, 23);
		chckbxCool.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(chckbxCool);

		chckbxCut = new JCheckBox("Cut");
		chckbxCut.setBounds(285, 91, 50, 23);
		chckbxCut.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(chckbxCut);

		JLabel lblPackage = new JLabel("Package");
		lblPackage.setBounds(10, 124, 50, 14);
		lblPackage.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblPackage);

		JLabel lblAdditionalNotes = new JLabel("Notes:");
		lblAdditionalNotes.setBounds(10, 152, 81, 14);
		lblAdditionalNotes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblAdditionalNotes);

		textArea = new JTextArea();
		textArea.setBounds(90, 152, 347, 99);
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(textArea);

		JLabel lblDeliveryTime = new JLabel("Delivery time:");
		lblDeliveryTime.setBounds(10, 268, 100, 14);
		lblDeliveryTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(lblDeliveryTime);

		deadline = new DateTimePicker();
		deadline.setBounds(90, 39, 347, 20);
		deadline.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(deadline);

		deliveryTime = new DateTimePicker();
		deliveryTime.setBounds(90, 265, 347, 20);
		deliveryTime.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPanel.add(deliveryTime);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 298, 452, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
				buttonPane.add(cancelButton);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void loadDataForComboBoxes() {
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

		String[] comboPackage = new String[packageList.size()];
		counter = 0;
		for (Package p : packageList) {
			comboPackage[counter] = p.getPackageName();
			counter++;
		}
		DefaultComboBoxModel<String> cbmPackage = new DefaultComboBoxModel<>(comboPackage);
		comboBoxPackage.setModel(cbmPackage);
		contentPanel.add(comboBoxPackage);
	}

	public String[] getNewRow() {
		String itemSelected = comboBoxItem.getSelectedItem().toString();
		String deadlineTimeString = new DateTimePickerParser().getDateTimeForDateTimePicker(deadline);
		String deliveryTimeString = new DateTimePickerParser().getDateTimeForDateTimePicker(deliveryTime);
		String packageSelected = comboBoxPackage.getSelectedItem().toString();
		String delivery = chckbxDelivery.isSelected() ? "Yes" : "No";
		String cool = chckbxCool.isSelected() ? "Yes" : "No";
		String cut = chckbxCut.isSelected() ? "Yes" : "No";
		String[] returnRow = { itemSelected, deadlineTimeString, txtWeight.getText(), delivery, cool, cut, packageSelected, textArea.getText(), txtAmount.getText(), deliveryTimeString };
		return returnRow;
	}

	public void fillFields(DefaultTableModel model, JTable table) {

		int selectedRow = table.getSelectedRow();

		String selectedItem = model.getValueAt(selectedRow, 0).toString();
		String deadlineTime = model.getValueAt(selectedRow, 1).toString();
		String weight = model.getValueAt(selectedRow, 2).toString();
		String delivery = model.getValueAt(selectedRow, 3).toString();
		String cool = model.getValueAt(selectedRow, 4).toString();
		String cut = model.getValueAt(selectedRow, 5).toString();
		String itemPackage = model.getValueAt(selectedRow, 6).toString();
		String additionalNotes = model.getValueAt(selectedRow, 7).toString();
		String amount = model.getValueAt(selectedRow, 8).toString();
		String deliveryString = model.getValueAt(selectedRow, 9).toString();

		comboBoxItem.setSelectedItem(selectedItem);
		deadline.setDateTimePermissive(new DateTimePickerParser().dateStringIntoLocalDateTime(deadlineTime));
		txtWeight.setText(weight);
		chckbxDelivery.setSelected((delivery.equalsIgnoreCase("Yes") ? true : false));
		chckbxCool.setSelected((cool.equalsIgnoreCase("Yes") ? true : false));
		chckbxCut.setSelected((cut.equalsIgnoreCase("Yes") ? true : false));
		comboBoxPackage.setSelectedItem(itemPackage);
		textArea.setText(additionalNotes);
		txtAmount.setText(amount);
		deliveryTime.setDateTimePermissive(new DateTimePickerParser().dateStringIntoLocalDateTime(deliveryString));

	}

	public void addConfirmListener(ActionListener listener) {
		okButton.addActionListener(listener);
	}

	public void addCancelListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

	public JComboBox getComboBoxPackage() {
		return comboBoxPackage;
	}

	public void setComboBoxPackage(JComboBox comboBoxPackage) {
		this.comboBoxPackage = comboBoxPackage;
	}

	public JComboBox getComboBoxItem() {
		return comboBoxItem;
	}

	public void setComboBoxItem(JComboBox comboBoxItem) {
		this.comboBoxItem = comboBoxItem;
	}

	public JTextField getTxtWeight() {
		return txtWeight;
	}

	public void setTxtWeight(JTextField txtWeight) {
		this.txtWeight = txtWeight;
	}

	public JTextField getTxtAmount() {
		return txtAmount;
	}

	public void setTxtAmount(JTextField txtAmount) {
		this.txtAmount = txtAmount;
	}

	public DateTimePicker getDeadline() {
		return deadline;
	}

	public void setDeadline(DateTimePicker deadline) {
		this.deadline = deadline;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JCheckBox getChckbxDelivery() {
		return chckbxDelivery;
	}

	public void setChckbxDelivery(JCheckBox chckbxDelivery) {
		this.chckbxDelivery = chckbxDelivery;
	}

	public JCheckBox getChckbxCool() {
		return chckbxCool;
	}

	public void setChckbxCool(JCheckBox chckbxCool) {
		this.chckbxCool = chckbxCool;
	}

	public JCheckBox getChckbxCut() {
		return chckbxCut;
	}

	public void setChckbxCut(JCheckBox chckbxCut) {
		this.chckbxCut = chckbxCut;
	}

}
