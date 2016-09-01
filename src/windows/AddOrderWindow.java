package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.github.lgooddatepicker.components.DateTimePicker;

import gsonObjects.Item;
import gsonObjects.Package;
import gsonObjects.Status;
import implementations.DateTimePickerParser;
import implementations.ItemImpl;
import implementations.OrderImpl;
import implementations.PackageImpl;
import implementations.StatusImpl;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			setIconImage(ImageIO.read(classLoader.getResourceAsStream("Dining Room-48.png")));
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.userId = userId;
		setFont(new Font("SansSerif", Font.PLAIN, 16));
		setTitle("Add Order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboBoxStatus = new JComboBox();
		comboBoxStatus.setBounds(532, 20, 329, 20);
		comboBoxStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
		loadDataForComboBoxes();

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(31, 23, 85, 14);
		lblName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(lblName);

		JLabel lblAdress = new JLabel("Adress:");
		lblAdress.setBounds(31, 53, 85, 14);
		lblAdress.setFont(new Font("SansSerif", Font.PLAIN, 12));
		;
		contentPane.add(lblAdress);

		JLabel lblOrderDate = new JLabel("Order Date:");
		lblOrderDate.setBounds(31, 84, 85, 14);
		lblOrderDate.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(lblOrderDate);

		JLabel lblAdditionalNotes = new JLabel("Notes:");
		lblAdditionalNotes.setBounds(430, 53, 85, 14);
		lblAdditionalNotes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(lblAdditionalNotes);

		JLabel lblStatusId = new JLabel("Status:");
		lblStatusId.setBounds(430, 23, 85, 14);
		lblStatusId.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(lblStatusId);

		txtName = new JTextField();
		txtName.setBounds(126, 20, 281, 20);
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtAdress = new JTextField();
		txtAdress.setBounds(126, 50, 281, 20);
		txtAdress.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(txtAdress);
		txtAdress.setColumns(10);

		txtAreaAdditionalNotes = new JTextArea();
		txtAreaAdditionalNotes.setBounds(532, 51, 329, 50);
		txtAreaAdditionalNotes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(txtAreaAdditionalNotes);

		dateTimePicker = new DateTimePicker();
		dateTimePicker.setBounds(126, 81, 281, 20);
		dateTimePicker.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(dateTimePicker);

		loadInitialTable();
		itemTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		JTableHeader itemHeader = itemTable.getTableHeader();
		itemHeader.setOpaque(false);
		itemHeader.setBackground(Color.LIGHT_GRAY);
		itemHeader.setForeground(Color.BLACK);
		JScrollPane jsp = new JScrollPane(itemTable);
		jsp.setBounds(126, 112, 732, 195);
		contentPane.add(jsp);

		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(31, 112, 46, 14);
		lblItems.setFont(new Font("SansSerif", Font.PLAIN, 12));
		contentPane.add(lblItems);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(772, 315, 89, 23);
		btnRemove.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (itemTable.getSelectedRow() != -1) {
					model.removeRow(itemTable.getSelectedRow());
				}
			}
		});
		contentPane.add(btnRemove);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(673, 315, 89, 23);
		btnEdit.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (itemTable.getSelectedRow() != -1) {
					AddItemDialog aid = new AddItemDialog(itemList, packageList);
					aid.fillFields(model, itemTable);
					aid.setVisible(true);
					aid.addConfirmListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							model.removeRow(itemTable.getSelectedRow());
							model.addRow(aid.getNewRow());
							aid.dispose();
						}
					});

					aid.addCancelListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							aid.dispose();
						}
					});
				}
			}
		});
		contentPane.add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(575, 315, 89, 23);
		btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddItemDialog aid = new AddItemDialog(itemList, packageList);
				aid.setVisible(true);
				aid.addConfirmListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						model.addRow(aid.getNewRow());
						aid.dispose();
					}
				});

				aid.addCancelListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						aid.dispose();
					}
				});
			}
		});
		contentPane.add(btnAdd);

		JButton btnCreateOrder = new JButton("Order");
		btnCreateOrder.setBounds(10, 349, 851, 36);
		btnCreateOrder.setFont(new Font("SansSerif", Font.PLAIN, 12));
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
		ItemImpl ii = new ItemImpl();
		itemList = ii.getItemList();

		// load values for packages
		PackageImpl pi = new PackageImpl();
		packageList = pi.getPackageList();
	}

	private void loadInitialTable() {
		String[] columnNames = { "Item", "Deadline", "Weight", "Delivery", "Cool", "Cut", "Package", "Additional notes", "Amount", "Delivery time" };
		model = new DefaultTableModel();
		for (String column : columnNames) {
			model.addColumn(column);
		}
		itemTable = new JTable(model);
	}

	private void sendAddOrderRequest() {
		OrderImpl orderImpl = new OrderImpl();
		StatusImpl statusImpl = new StatusImpl();
		int orderId = orderImpl.sendAddOrderRequest(txtName.getText(), txtAdress.getText(), new DateTimePickerParser().getDateTimeForDateTimePicker(dateTimePicker), txtAreaAdditionalNotes.getText(), statusImpl.getStatusId(statusList, comboBoxStatus.getSelectedItem().toString()), userId);
		System.out.println("Adder order with id: " + orderId);

		if (orderImpl.addItemsToOrder(orderId, model, itemList, packageList)) {
			JOptionPane.showConfirmDialog(null, "Order added successfully", "Order add success!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			this.setVisible(false);
			this.dispose();
		} else {
			JOptionPane.showConfirmDialog(null, "Order add has failed", "Order add fail!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		}

	}
}
