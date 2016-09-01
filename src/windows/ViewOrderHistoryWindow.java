package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import gsonObjects.Item;
import gsonObjects.Order;
import gsonObjects.OrderItems;
import gsonObjects.Package;
import gsonObjects.Status;
import implementations.ItemImpl;
import implementations.OrderImpl;
import implementations.PackageImpl;
import implementations.StatusImpl;

import java.awt.Component;

public class ViewOrderHistoryWindow extends JFrame {

	private JPanel contentPane;
	private JTable orderTable;
	private JTable itemTable;
	private DefaultTableModel orderModel;
	private DefaultTableModel itemsModel;
	private int userId;
	private List<Status> statusList;
	private List<Package> packageList;
	private List<Item> itemList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewOrderHistoryWindow frame = new ViewOrderHistoryWindow(-1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewOrderHistoryWindow(int userId) {
		this.userId = userId;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 805, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loadTablesAndLists();
		JScrollPane jspOrder = new JScrollPane(orderTable);
		jspOrder.setBounds(5, 11, 774, 278);
		contentPane.add(jspOrder);

		JScrollPane jspItems = new JScrollPane(itemTable);
		jspItems.setBounds(5, 300, 774, 278);
		contentPane.add(jspItems);
	}

	private void loadTablesAndLists() {

		statusList = new StatusImpl().getAllStatuses();
		itemList = new ItemImpl().getItemList();
		packageList = new PackageImpl().getPackageList();

		String[] orderColumnNames = { "ID", "Ordered by", "Adress", "Order date", "Additional notes", "Status" };
		orderModel = new DefaultTableModel();
		for (String column : orderColumnNames) {
			orderModel.addColumn(column);
		}
		OrderImpl orderImpl = new OrderImpl();
		List<Order> orderList = orderImpl.getOrderList(userId);
		fillOrderTable(orderList);
		orderTable = new JTable(orderModel);
		orderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				cleanOrderItemModel();
				fillItemsTable(orderTable.getValueAt(orderTable.getSelectedRow(), 0).toString());
			}
		});

		String[] itemsColumnNames = { "Item", "Deadline", "Weight", "Delivery", "Cool", "Cut", "Package", "Additional notes", "Amount", "Delivery time" };
		itemsModel = new DefaultTableModel();
		for (String column : itemsColumnNames) {
			itemsModel.addColumn(column);

		}
		itemTable = new JTable(itemsModel);

	}

	private void fillOrderTable(List<Order> orderList) {
		for (Order o : orderList) {
			String[] row = { String.valueOf(o.getOrderId()), o.getOrderOrdered(), o.getOrderAdress(), o.getOrderDate(), o.getAdditionalNotes(), new StatusImpl().getStatusName(statusList, o.getStatusId()) };
			orderModel.addRow(row);
		}
	}

	private void cleanOrderItemModel() {
		if (itemsModel.getRowCount() > 0) {
			for (int i = itemsModel.getRowCount() - 1; i > -1; i--) {
				itemsModel.removeRow(i);
			}
		}
	}

	private void fillItemsTable(String idOrder) {
		List<OrderItems> orderItemsList = new OrderImpl().getOrderItemsList(idOrder);
		if(orderItemsList != null && orderItemsList.size()>0){
		for (OrderItems oi : orderItemsList) {
			String[] row = { new ItemImpl().getItemName(itemList, oi.getId_item()), oi.getDeadline(), String.valueOf(oi.getWeight()), (oi.getDelivery() == 1 ? "Yes" : "No"), (oi.getCool() == 1 ? "Yes" : "No"), (oi.getCut() == 1 ? "Yes" : "No"), new PackageImpl().getPackageName(packageList, oi.getId_package()), oi.getAdditionalNotes(), String.valueOf(oi.getAmount()), oi.getDeliveryTime() };
			itemsModel.addRow(row);
		}
		}
	}
}
