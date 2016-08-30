package windows;

import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.components.DateTimePicker;

import gsonObjects.Status;
import implementations.StatusImpl;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddOrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -156197776011157836L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStatus;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public AddOrderWindow(int userId) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Deliver Food-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("User id is: " + userId);
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setTitle("Add Order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(532, 20, 329, 20);
		LoadDataForComboBoxes();

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(31, 23, 85, 14);
		contentPane.add(lblName);

		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(31, 53, 85, 14);
		contentPane.add(lblSurname);

		JLabel lblOrderDate = new JLabel("Order Date:");
		lblOrderDate.setBounds(31, 84, 85, 14);
		contentPane.add(lblOrderDate);

		JLabel lblAdditionalNotes = new JLabel("Additional Notes:");
		lblAdditionalNotes.setBounds(430, 53, 85, 14);
		contentPane.add(lblAdditionalNotes);

		JLabel lblStatusId = new JLabel("Status:");
		lblStatusId.setBounds(430, 23, 85, 14);
		contentPane.add(lblStatusId);

		txtName = new JTextField();
		txtName.setBounds(126, 20, 281, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.setBounds(126, 50, 281, 20);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);

		JTextArea txtAreaAdditionalNotes = new JTextArea();
		txtAreaAdditionalNotes.setBounds(532, 51, 329, 50);
		contentPane.add(txtAreaAdditionalNotes);

		DateTimePicker dateTimePicker = new DateTimePicker();
		dateTimePicker.setBounds(126, 81, 281, 20);
		contentPane.add(dateTimePicker);

		/*JTextArea textArea = new JTextArea();
		textArea.setBounds(126, 112, 732, 195);
		contentPane.add(textArea);*/

		JTable itemTable = addOrder();
		itemTable.setBounds(126, 112, 732, 195);
		contentPane.add(itemTable);

		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(31, 112, 46, 14);
		contentPane.add(lblItems);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(772, 315, 89, 23);
		contentPane.add(btnRemove);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(673, 315, 89, 23);
		contentPane.add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(575, 315, 89, 23);
		contentPane.add(btnAdd);

		JButton btnCreateOrder = new JButton("Order");
		btnCreateOrder.setBounds(10, 349, 851, 36);
		contentPane.add(btnCreateOrder);
	}

	@SuppressWarnings("unchecked")
	private void LoadDataForComboBoxes() {
		StatusImpl si = new StatusImpl();
		List<Status> statusList = si.getAllStatuses();
		String[] comboStatus = new String[statusList.size()];
		int counter = 0;
		for (Status s : statusList) {
			comboStatus[counter] = s.getStatusName();
			counter++;
		}
		DefaultComboBoxModel<String> cbmStatus = new DefaultComboBoxModel<>(comboStatus);
		comboBoxStatus.setModel(cbmStatus);
		contentPane.add(comboBoxStatus);
	}

	private JTable addOrder() {
		//String[] columnNames = { "ID Item", "Deadline", "Weight", "Start time", "Delivery", "Cool", "Cut", "ID Package", "Additional notes", "Amount"};

		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};

		JTable table = new JTable(data, columnNames);
		return table;
	}
}
