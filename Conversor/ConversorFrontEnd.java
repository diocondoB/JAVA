/**
 * 
 * 
 * **/


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class ConversorFrontEnd {

	//------------------------------VARIABLES----------------------------//
		private JFrame frmConversorAlura;
		private JTextField OrigentextField;
		private JTextField DestinotextField;
		private JComboBox<String> ListaOrigen;
		private JComboBox<String> ListaDestino;
		private DefaultComboBoxModel<String> monedaModel;
		private DefaultComboBoxModel<String> temperaturaModel;
		private DefaultComboBoxModel<String> MasaModel;

		int seleccionOrigenPrevio = -1;
	//----------------------------------------------------------------------//

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorFrontEnd window = new ConversorFrontEnd();
					window.frmConversorAlura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	public ConversorFrontEnd() {
		initialize();
	}

	
	private void initialize() {
		
		// ------------------------------------LISTAS------------------------//
		monedaModel = new DefaultComboBoxModel<>();
		monedaModel.addElement("Dólar");
		monedaModel.addElement("Euro");
		monedaModel.addElement("Peso Argentino");

		temperaturaModel = new DefaultComboBoxModel<>();
		temperaturaModel.addElement("Celsius");
		temperaturaModel.addElement("Fahrenheit");
		temperaturaModel.addElement("Kelvin");

		MasaModel = new DefaultComboBoxModel<>();
		MasaModel.addElement("Tonelada");
		MasaModel.addElement("Kilogramo");
		MasaModel.addElement("Gramo");
		MasaModel.addElement("Miligramo");
//-----------------------------------------------------------------------------//

		frmConversorAlura = new JFrame();
		frmConversorAlura.setTitle("Conversor | ALURA");
		frmConversorAlura.setBounds(100, 100, 442, 350);
		frmConversorAlura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConversorAlura.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Elija la unidad a convertir");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNewLabel.setBounds(130, 11, 191, 30);
		frmConversorAlura.getContentPane().add(lblNewLabel);

		// ---PARA EVITAR SELECCION MULTIPLE EN BOTON MASA, TEMP Y MASA -----//
		ButtonGroup buttonGroup = new ButtonGroup();
		// -------------------------------------------------------------------//

		
		OrigentextField = new JTextField();
		OrigentextField.setFont(new Font("Calibri", Font.PLAIN, 20));
		OrigentextField.setBounds(15, 154, 150, 96);
		frmConversorAlura.getContentPane().add(OrigentextField);
		OrigentextField.setColumns(10);
//---------------------------- se desarrolla al cierre del metodo ------------//
		OrigentextField.addKeyListener(new MyKeyListener());
//---------------------------------------------------------------------------//

		DestinotextField = new JTextField();
		DestinotextField.setEditable(false);
		DestinotextField.setFont(new Font("Calibri", Font.PLAIN, 20));
		DestinotextField.setColumns(10);
		DestinotextField.setBounds(252, 154, 150, 96);
		frmConversorAlura.getContentPane().add(DestinotextField);

		
		
		//-----------------------------botones 1º monedas, 2º temperatura ,3º masa--------------------------//
		JRadioButton MonedaButton = new JRadioButton("Monedas");
		MonedaButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		MonedaButton.setBounds(15, 58, 109, 23);
		frmConversorAlura.getContentPane().add(MonedaButton);
		buttonGroup.add(MonedaButton);
		// -----------------EVENTO PARA MOSTRAR LA LISTA---------------------//
		MonedaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrigen.setModel(monedaModel);
				ListaDestino.setEnabled(false);
				ListaDestino.removeAllItems();
				seleccionOrigenPrevio = -1;
				OrigentextField.setText("");
				DestinotextField.setText("");
			}
		});

		JRadioButton TemperaturaButton = new JRadioButton("Temperaturas");
		TemperaturaButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		TemperaturaButton.setBounds(162, 58, 127, 23);
		frmConversorAlura.getContentPane().add(TemperaturaButton);
		buttonGroup.add(TemperaturaButton);
		// -----------------EVENTO PARA MOSTRAR LA LISTA---------------------//
		TemperaturaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrigen.setModel(temperaturaModel);
				ListaDestino.setEnabled(false);
				ListaDestino.removeAllItems();
				seleccionOrigenPrevio = -1;
				OrigentextField.setText("");
				DestinotextField.setText("");
			}
		});

		JRadioButton MasaButton = new JRadioButton("Masa");
		MasaButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		MasaButton.setBounds(315, 58, 109, 23);
		frmConversorAlura.getContentPane().add(MasaButton);
		buttonGroup.add(MasaButton);
		// -----------------EVENTO PARA MOSTRAR LA LISTA---------------------//
		MasaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaOrigen.setModel(MasaModel);
				ListaDestino.setEnabled(false);
				ListaDestino.removeAllItems();
				seleccionOrigenPrevio = -1;
				OrigentextField.setText("");
				DestinotextField.setText("");
			}
		});
		
		
		ListaOrigen = new JComboBox();
		ListaOrigen.setFont(new Font("Calibri", Font.PLAIN, 16));
		ListaOrigen.setBounds(15, 120, 150, 30);
		frmConversorAlura.getContentPane().add(ListaOrigen);
		// -----------------EVENTO PARA MOSTRAR LA LISTA ---------------------//
		ListaOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ListaOrigen.getSelectedIndex() != seleccionOrigenPrevio) { // SeleccionOrigenPrevio =-1
					seleccionOrigenPrevio = ListaOrigen.getSelectedIndex();
					ListaDestino.setEnabled(true);
					ListaDestino.removeAllItems();
							//---- EVENTO PARA MOSTRAR LISTA DE DESTINO SEGUN LA UNIDAD A CONVERTIR-----------
					if (MonedaButton.isSelected()) {
					    for (int i = 0; i < monedaModel.getSize(); i++) {
					        if (i != seleccionOrigenPrevio) {
					            ListaDestino.addItem(monedaModel.getElementAt(i));
					        } }
					} else if (TemperaturaButton.isSelected()) {
					    for (int i = 0; i < temperaturaModel.getSize(); i++) {
					        if (i != seleccionOrigenPrevio) {
					            ListaDestino.addItem(temperaturaModel.getElementAt(i));
					        }}
					} else if (MasaButton.isSelected()) {
					    for (int i = 0; i < MasaModel.getSize(); i++) {
					        if (i != seleccionOrigenPrevio) {
					            ListaDestino.addItem(MasaModel.getElementAt(i));
					        }}}
				}}});

		ListaDestino = new JComboBox();
		ListaDestino.setFont(new Font("Calibri", Font.PLAIN, 16));
		ListaDestino.setBounds(252, 120, 150, 30);
		frmConversorAlura.getContentPane().add(ListaDestino);


	
	}

	
	//--------------------------EVENTO MYKEYLISTENER, hace las tasas de conversion ------------------------------------//
	private class MyKeyListener implements KeyListener {
		@Override
		// -----------------EVENTO PARA PERMITIR SOLO NUMEROS EL CARACTER " ." Y " ," ---------------------//
		public void keyTyped(KeyEvent e) {
			char inputChar = e.getKeyChar();
			if (!Character.isDigit(inputChar) && inputChar != '.' && inputChar != ',') {
				e.consume();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		    String input = OrigentextField.getText().replace(',', '.');
		    if (!input.isEmpty()) {
		        double value = Double.parseDouble(input);

		        String From = (String) ListaOrigen.getSelectedItem();
		        String To = (String) ListaDestino.getSelectedItem();

		        double ratesFrom = 0;
		        double ratesTo = 1.0;

		        //-------------Valores para moneda
		        if (From.equals("Dólar") && To.equals("Euro")) {
		            ratesTo = 0.93; 
		        } else if (From.equals("Dólar") && To.equals("Peso Argentino")) {
		            ratesTo = 349.96;
		        } else if (From.equals("Euro") && To.equals("Dólar")) {
		            ratesTo = 1.07; 
		        } else if (From.equals("Euro") && To.equals("Peso Argentino")) {
		            ratesTo = 375.43; 
		        } else if (From.equals("Peso Argentino") && To.equals("Dólar")) {
		            ratesTo = 0.0027; 
		        } else if (From.equals("Peso Argentino") && To.equals("Euro")) {
		            ratesTo = 0.0029; 
		        }

		     // ----------------Valores para temperatura
	            if (From.equals("Celsius") && To.equals("Fahrenheit")) {
	                ratesTo = (9/5);
	                ratesFrom= 32;
	            } else if (From.equals("Celsius") && To.equals("Kelvin")) {
	            	ratesFrom = 273.15;
	            } else if (From.equals("Fahrenheit") && To.equals("Celsius")) {
	            	ratesTo = (5/9);
	            	ratesFrom= -32;
	            } else if (From.equals("Fahrenheit") && To.equals("Kelvin")) {
	                ratesTo = (5/9) ;
	                ratesFrom = 273.15 ;
	            } else if (From.equals("Kelvin") && To.equals("Celsius")) {
	                ratesTo = -273.1;
	            } else if (From.equals("Kelvin") && To.equals("Fahrenheit")) {
	                ratesTo = (9/5);
	                ratesFrom = -273.15;
	            }
	             //---------------------------- valores para masa
	                if (From.equals("Tonelada") && To.equals("Kilogramo")) {
	                    ratesTo = 1000;
	                } else if (From.equals("Tonelada") && To.equals("Gramo")) {
	                    ratesTo = 1000000;
	                } else if (From.equals("Tonelada") && To.equals("Miligramo")) {
	                    ratesTo = 1000000000;
	                } else if (From.equals("Kilogramo") && To.equals("Gramo")) {
	                    ratesTo = 1000;
	                } else if (From.equals("Kilogramo") && To.equals("Miligramo")) {
	                    ratesTo = 1000000;
	                } else if (From.equals("Kilogramo") && To.equals("Tonelada")) {
	                    ratesTo = 0.001;
	                } else if (From.equals("Gramo") && To.equals("Miligramo")) {
	                    ratesTo = 1000;
	                } else  if (From.equals("Gramo") && To.equals("Tonelada")) {
	                    ratesTo = 0.000001;
	                } else if (From.equals("Gramo") && To.equals("Kilogramo")) {
	                    ratesTo = 0.001;
	                } else if (From.equals("Miligramo") && To.equals("Tonelada")) {
	                    ratesTo = 0.000000001;
	                } else  if (From.equals("Miligramo") && To.equals("Kilogramo")) {
	                    ratesTo = 0.000001;
	                } else if (From.equals("Miligramo") && To.equals("Gramo")) {
	                    ratesTo = 0.001;
	                }
 
		        double newValue = (value * ratesTo) + ratesFrom;
		        DestinotextField.setText(String.format("%.2f", newValue));
		    } else {
		        OrigentextField.setText("");
				DestinotextField.setText("");
		    }
		}


		// ------------------------------------------------------------------------------------------------------------------------------------
		
		// --------------------------------------------------------------------------------------------------------------------------------

	}
}