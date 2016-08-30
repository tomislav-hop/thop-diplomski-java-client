package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DateTimePicker;

import gsonObjects.Item;
import gsonObjects.Package;
import gsonObjects.Status;
import implementations.DateTimePickerParser;
import implementations.StatusImpl;
import requests.AddOrder;
import requests.GetItemList;
import requests.GetPackageList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AddOrderWindow extends JFrame {

	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxStatus;
	private static final long serialVersionUID = -156197776011157836L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAdress;
	private JTextArea txtAreaAdditionalNotes;
	private DateTimePicker dateTimePicker;
	private JTable itemTable;
	private DefaultTableModel model;
	private List<Status> statusList;
	private List<Package> packageList;
	private List<Item> itemList;
	private int userId;

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
		this.userId = userId;
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
		loadDataForComboBoxes();

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(31, 23, 85, 14);
		contentPane.add(lblName);

		JLabel lblAdress = new JLabel("Adress:");
		lblAdress.setBounds(31, 53, 85, 14);
		contentPane.add(lblAdress);

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

		txtAdress = new JTextField();
		txtAdress.setBounds(126, 50, 281, 20);
		contentPane.add(txtAdress);
		txtAdress.setColumns(10);

		txtAreaAdditionalNotes = new JTextArea();
		txtAreaAdditionalNotes.setBounds(532, 51, 329, 50);
		contentPane.add(txtAreaAdditionalNotes);

		dateTimePicker = new DateTimePicker();
		dateTimePicker.setBounds(126, 81, 281, 20);
		contentPane.add(dateTimePicker);

		loadInitialTable();
		JScrollPane jsp = new JScrollPane(itemTable);
		jsp.setBounds(126, 112, 732, 195);
		contentPane.add(jsp);

		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(31, 112, 46, 14);
		contentPane.add(lblItems);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(772, 315, 89, 23);
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		contentPane.add(btnRemove);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(673, 315, 89, 23);
		contentPane.add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(575, 315, 89, 23);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddItemDialog aid = new AddItemDialog(itemList, packageList);
				aid.setVisible(true);
				aid.addConfirmListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("OK");
						// System.out.println("COOL: " +
						// aid.getChckbxCool().isSelected());
						model.addRow(aid.getNewRow());
						aid.dispose();
					}
				});

				aid.addCancelListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Cancel");
						aid.dispose();
					}
				});
			}
		});
		contentPane.add(btnAdd);

		JButton btnCreateOrder = new JButton("Order");
		btnCreateOrder.setBounds(10, 349, 851, 36);
		btnCreateOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendAddOrderRequest();
			}
		});
		contentPane.add(btnCreateOrder);
	}

	@SuppressWarnings("unchecked")
	private void loadDataForComboBoxes() {
		// load status list and fill combo box
		StatusImpl si = new StatusImpl();
		statusList = si.getAllStatuses();
		String[] comboStatus = new String[statusList.size()];
		int counter = 0;
		for (Status s : statusList) {
			comboStatus[counter] = s.getStatusName();
			counter++;
		}
		DefaultComboBoxModel<String> cbmStatus = new DefaultComboBoxModel<>(comboStatus);
		comboBoxStatus.setModel(cbmStatus);
		contentPane.add(comboBoxStatus);

		// load values for items
		GetItemList gil = new GetItemList();
		itemList = gil.getItemList();

		// load values for packages
		GetPackageList gpl = new GetPackageList();
		packageList = gpl.getPackageList();
	}

	private void loadInitialTable() {
		String[] columnNames = { "Item", "Deadline", "Weight", "Delivery", "Cool", "Cut", "Package", "Additional notes", "Amount", "Delivery time" };
		model = new DefaultTableModel();
		for (String column : columnNames) {
			model.addColumn(column);
		}
		itemTable = new JTable(model);
	}

	private int getStatusId() {
		for (Status s : statusList) {
			if (s.getStatusName().equalsIgnoreCase(comboBoxStatus.getSelectedItem().toString())) {
				return s.getStatusId();
			}
		}
		return -1;
	}

	private void sendAddOrderRequest() {
		AddOrder ao = new AddOrder();
		int orderId = ao.sendAddOrderRequest(txtName.getText(), txtAdress.getText(), new DateTimePickerParser().getDateTimeForDateTimePicker(dateTimePicker), txtAreaAdditionalNotes.getText(), getStatusId(), userId);
		System.out.println("Adder order with id: " + orderId);
		
		ao.addItemsToOrder(orderId, model, itemList, packageList);
		
	}
}
