package com.hotel_alura.views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hotel_alura.controllers.CrudBookinDAO;
import com.hotel_alura.controllers.CrudGuestDAO;
import com.hotel_alura.models.Booking;
import com.hotel_alura.models.Guest;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.sql.Date;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	
	CrudBookinDAO crudBookingDAO = new CrudBookinDAO();
	CrudGuestDAO crudGuestDAO= new CrudGuestDAO();
	List<Booking> bookings = crudBookingDAO.getAllBookings();
	List<Guest> guests= crudGuestDAO.getAllGuests();
	
	String selectedTable="reservas";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 332, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setName("Reservas");
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);

		for (Booking booking : bookings) {
		    Object[] row = new Object[5];
		    row[0] = booking.getIdBooking();
		    row[1] = booking.getEntryDate();
		    row[2] = booking.getExitDate();
		    row[3] = booking.getValue();
		    row[4] = booking.getPaymentMethod();
		    modelo.addRow(row);
		}
		tbReservas.getModel().addTableModelListener(new TableModelListener() {
		    public void tableChanged(TableModelEvent e) {
		        if (e.getType() == TableModelEvent.UPDATE) { // verifica que la acción sea una actualización
		            int row = e.getFirstRow();
		            TableModel model = (TableModel)e.getSource();
		            
		            // obtén los datos de la fila modificada
		            int numeroReserva = (int) model.getValueAt(row, 0);
		            Date fechaCheckIn = (Date) model.getValueAt(row, 1);
		            Date fechaCheckOut =(Date) model.getValueAt(row, 2);
		            BigDecimal valor = new BigDecimal(model.getValueAt(row, 3).toString());
		            String formaPago = model.getValueAt(row, 4).toString();
		            
		            Booking bookingEdit= new Booking(fechaCheckIn, fechaCheckOut, valor, formaPago);
		            bookingEdit.setIdBooking(numeroReserva);
		            
		            // aquí puedes agregar la lógica para actualizar la base de datos con los nuevos valores
		            crudBookingDAO.updateBooking(bookingEdit);
		            
		            // puedes usar el número de reserva para identificar la reserva que se modificó
		            System.out.println("Reserva modificada: " + numeroReserva);
		        }
		    }
		});
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHuespedes.setName("Huespedes");
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		for (Guest  guest: guests) {
		    Object[] row = new Object[7];
		    row[0] = guest.getIdGuest();
		    row[1] = guest.getName();
		    row[2] = guest.getLastName();
		    row[3] = guest.getDateOfBirth();
		    row[4] = guest.getNationality();
		    row[5] = guest.getPhoneNumber();
		    row[6] = guest.getBookingId();
		    modeloHuesped.addRow(row);
		}
		
		tbHuespedes.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE) {
					int row = e.getFirstRow();
		            TableModel model = (TableModel)e.getSource();
		            //campos:
		            int idGuest= (int) model.getValueAt(row, 0);
		            String name=  (String) model.getValueAt(row, 1);
		            String lastName= (String) model.getValueAt(row, 2);
		            Date dateOfbirth= (Date) model.getValueAt(row, 3);
		            String nationality= (String) model.getValueAt(row, 4);
		            String phoneNumber= (String) model.getValueAt(row, 5);
		            int idBooking= (int) model.getValueAt(row, 6);
		            Guest guestEdit= new Guest(name, lastName, dateOfbirth, nationality, phoneNumber, idBooking);
		            guestEdit.setIdGuest(idGuest);
		            
		            crudGuestDAO.updateGuest(guestEdit);
		            
				}
				
			}
		});
		panel.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        String title = sourceTabbedPane.getTitleAt(index);
		        if (title.equals("Reservas")) {
		            selectedTable="reservas";
		        } else if (title.equals("Huéspedes")) {
		        	selectedTable="huespedes";
		        }
		    }
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(selectedTable=="reservas") {
					String  idSearch = txtBuscar.getText();
					if(idSearch != null && !idSearch.trim().isEmpty()) {
						List<Booking> bookingsFound = crudBookingDAO.searchBookingsById(Integer.parseInt(idSearch));
						modelo.setRowCount(0);
				        for (Booking booking : bookingsFound) {
				        	Object[] row = new Object[5];
				            row[0] = booking.getIdBooking();
				            row[1] = booking.getEntryDate();
				            row[2] = booking.getExitDate();
				            row[3] = booking.getValue();
				            row[4] = booking.getPaymentMethod();
				            modelo.addRow(row);
				        }
						System.out.println(txtBuscar.getText());
					}else {
						modelo.setRowCount(0);
						for (Booking booking : bookings) {
						    Object[] row = new Object[5];
						    row[0] = booking.getIdBooking();
						    row[1] = booking.getEntryDate();
						    row[2] = booking.getExitDate();
						    row[3] = booking.getValue();
						    row[4] = booking.getPaymentMethod();
						    modelo.addRow(row);
						}
					}
				}
				if(selectedTable=="huespedes") {
					String lastName=txtBuscar.getText();
					if(lastName != null && !lastName.trim().isEmpty()) {
						List<Guest> guestsFound= crudGuestDAO.searchGuestByLastName(lastName);
						modeloHuesped.setRowCount(0);
						for (Guest  guest: guestsFound) {
						    Object[] row = new Object[7];
						    row[0] = guest.getIdGuest();
						    row[1] = guest.getName();
						    row[2] = guest.getLastName();
						    row[3] = guest.getDateOfBirth();
						    row[4] = guest.getNationality();
						    row[5] = guest.getPhoneNumber();
						    row[6] = guest.getBookingId();
						    modeloHuesped.addRow(row);
						}
					}else {
						modeloHuesped.setRowCount(0);
						for (Guest  guest: guests) {
						    Object[] row = new Object[7];
						    row[0] = guest.getIdGuest();
						    row[1] = guest.getName();
						    row[2] = guest.getLastName();
						    row[3] = guest.getDateOfBirth();
						    row[4] = guest.getNationality();
						    row[5] = guest.getPhoneNumber();
						    row[6] = guest.getBookingId();
						    modeloHuesped.addRow(row);
						}
					}
				}
				
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		btnbuscar.addMouseListener(new MouseAdapter() {
		});
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
			
				JTable table = tbReservas;
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					System.out.println("Reservas aaaa");
					table.getCellEditor().stopCellEditing();
					table.clearSelection();
					table.transferFocus();
				}					
				
				JTable table2 = tbHuespedes;
				int selectedRow2 = table2.getSelectedRow();
				if (selectedRow2 != -1) {
					System.out.println("huespedes");
					table2.getCellEditor().stopCellEditing();
					table2.clearSelection();
					table2.transferFocus();
				}					
					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				btnEditar.setBackground(new Color(75, 162, 162));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				btnEditar.setBackground(new Color(12, 138, 199));
			}
		});
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				
				JTable table = tbReservas;
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					int idBooking = (int) tbReservas.getValueAt(selectedRow, 0);
					crudBookingDAO.deleteBooking(idBooking);
					System.out.println("TbReservas: "+selectedRow+ " id: "+idBooking);
					
				}
				
				JTable table2 = tbHuespedes;
				int selectedRow2 = table2.getSelectedRow();
				if (selectedRow2 != -1) {
					int idGuest= (int)tbHuespedes.getValueAt(selectedRow2, 0);
					crudGuestDAO.deleteGuest(idGuest);
					System.out.println("TbHuespedes: "+selectedRow2+" id: "+idGuest);
				}

		            
	            //Creando una nueva instancia para volver a cargar los datos de la bd una vez borrada uno de ellos.
	            CrudBookinDAO crudDAODel = new CrudBookinDAO();
	            CrudGuestDAO crudGDel= new CrudGuestDAO();
	        	List<Booking> bookingDel = crudDAODel.getAllBookings();
	        	List<Guest> guestDel= crudGDel.getAllGuests();

		        //Establecer los nuevos datos en el modelo de la tabla Reservas
		        modelo.setRowCount(0); // Establecer el número de filas en cero
		        for (Booking booking : bookingDel) {
		            Object[] row = new Object[5];
		            row[0] = booking.getIdBooking();
		            row[1] = booking.getEntryDate();
		            row[2] = booking.getExitDate();
		            row[3] = booking.getValue();
		            row[4] = booking.getPaymentMethod();
		            modelo.addRow(row); // Agregar las nuevas filas al modelo
		         }
		        modeloHuesped.setRowCount(0);
		        for (Guest  guest: guestDel) {
				    Object[] row = new Object[7];
				    row[0] = guest.getIdGuest();
				    row[1] = guest.getName();
				    row[2] = guest.getLastName();
				    row[3] = guest.getDateOfBirth();
				    row[4] = guest.getNationality();
				    row[5] = guest.getPhoneNumber();
				    row[6] = guest.getBookingId();
				    modeloHuesped.addRow(row);
				}
		         // Actualizar la vista de la tabla
		         tbReservas.repaint();
		         tbHuespedes.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				btnEliminar.setBackground(new Color(75, 162, 162));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				btnEliminar.setBackground(new Color(12, 138, 199));
			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
