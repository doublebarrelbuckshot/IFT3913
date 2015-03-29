

package met;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ListSelectionListener, ActionListener{

	private Model mod;
	private final JList<String> listClasses;
	private final JList<String> listGeneralizations;
	private final JList<String> listAttributes;
	private final JList<String> listOperations;
	private final JList<String> listRelations;
	private final JList<String> listMetrics;

	private final JTextArea descriptionTextArea;

	private final DefaultListModel<String> classDefaultModel = new DefaultListModel<>();
	private final DefaultListModel<String> generalizationsDefaultModel = new DefaultListModel<>();
	private final DefaultListModel<String> attributesDefaultModel = new DefaultListModel<>();
	private final DefaultListModel<String> operationsDefaultModel = new DefaultListModel<>();
	private final DefaultListModel<String> relationsDefaultModel = new DefaultListModel<>();
	private final DefaultListModel<String> metricsDefaultModel = new DefaultListModel<>();

	private ArrayList<Generalisation> generalization;
	private ArrayList<DataItem> attribute;
	private ArrayList<Operation> operation;
	private ArrayList<Relation> relation;
	private ArrayList<Metrics> metric;

	private Boolean programChanged = false;

	private String file_path;
	JTextArea FileNameLabel = new JTextArea("");


	@SuppressWarnings("unused")
	public static void main(String[] args){
		//LOAD THE WINDOW.
		//Inside the window we have the button and the button's action listener
		//If the button is clicked, go to action listener. It launches the function in Parse that creates the model
		//then, it updates the GUI interface with the model's info.
		GUI window = new GUI("TP1 - IFT3913 - Giancarlo Rizzi et Nedra Hamouda"); 
	}	

	public GUI(String s){
		super(s);

		/*
		 * SETUP FOR SOUTH AREA (TextArea)
		 */
		JPanel southPanel = new JPanel();

		this.descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setPreferredSize(new Dimension (400, 150));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		descriptionTextArea.setBorder(border);
		descriptionTextArea.setEditable(false);

		southPanel.add(descriptionTextArea);
		southPanel.setBorder(new TitledBorder("Description"));

		/*
		 * SETUP FOR NORTH AREA (FILE NAME AND BUTTON)
		 */
		JButton loadBtn = new JButton("Charger un fichier");
		JPanel loadPanel = new JPanel();
		loadPanel.add(loadBtn);
		loadBtn.addActionListener(this);

		FileNameLabel.setPreferredSize(new Dimension (400, 28));
		FileNameLabel.setBorder(border);
		FileNameLabel.setEditable(false);
		loadPanel.add(FileNameLabel);

		/*
		 * SETUP FOR CLASSES JLIST
		 */
		this.listClasses = new JList<>();
		listClasses.setModel(classDefaultModel);
		listClasses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listClasses.addListSelectionListener(this);		

		JScrollPane listScroller = new JScrollPane(listClasses);
		listScroller.setViewportView(listClasses);
		listScroller.setPreferredSize(new Dimension(250, 450));

		/*
		 * SETUP FOR CLASSES PANEL
		 */
		JPanel classPanel = new JPanel();
		classPanel.setBorder(new TitledBorder("Classes"));
		classPanel.add(listScroller, BorderLayout.CENTER);


		/*
		 * SETUP FOR GENERALIZATIONS JLIST
		 */

		this.listGeneralizations = new JList<>();
		listGeneralizations.setModel(generalizationsDefaultModel);
		listGeneralizations.addListSelectionListener(this);
		listGeneralizations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane listScrollerGeneralizations = new JScrollPane(listGeneralizations);
		listScrollerGeneralizations.setViewportView(listGeneralizations);
		listScrollerGeneralizations.setPreferredSize(new Dimension(250, 200));

		/*
		 * SETUP FOR GENERALIZATION PANEL
		 */
		setLayout(new BorderLayout());
		JPanel generalizationPanel = new JPanel();
		generalizationPanel.setBorder(new TitledBorder("Sous-Classes"));

		generalizationPanel.add(listScrollerGeneralizations, BorderLayout.CENTER);

		/*
		 * SETUP FOR ATTRIBUTES JLIST
		 */
		this.listAttributes = new JList<>();

		listAttributes.setModel(attributesDefaultModel);	
		listAttributes.addListSelectionListener(this);
		JScrollPane attributeScroller = new JScrollPane(listAttributes);
		attributeScroller.setViewportView(listAttributes);
		attributeScroller.setPreferredSize(new Dimension(250, 200));

		/*
		 * SETUP FOR ATTRIBUTES PANEL
		 */
		JPanel attributePanel = new JPanel();
		attributePanel.setBorder(new TitledBorder("Attributs"));
		attributePanel.add(attributeScroller, BorderLayout.CENTER);

		/*
		 * SETUP FOR OPERATIONS JLIST
		 */
		this.listOperations = new JList<>();

		listOperations.setModel(operationsDefaultModel);	
		listOperations.addListSelectionListener(this);		

		JScrollPane operationScroller = new JScrollPane(listOperations);
		operationScroller.setViewportView(listOperations);
		operationScroller.setPreferredSize(new Dimension(250, 200));

		/*
		 * SETUP FOR OPERATIONS PANEL
		 */
		JPanel operationPanel = new JPanel();
		operationPanel.setBorder(new TitledBorder("Operations"));
		operationPanel.add(operationScroller, BorderLayout.CENTER);

		/*
		 * SETUP FOR RELATIONS JLIST
		 */
		this.listRelations = new JList<>();

		listRelations.setModel(relationsDefaultModel);	
		listRelations.addListSelectionListener(this);		

		JScrollPane relationScroller = new JScrollPane(listRelations);
		relationScroller.setViewportView(listRelations);
		relationScroller.setPreferredSize(new Dimension(250, 200));

		/*
		 * SETUP FOR RELATIONS PANEL
		 */
		JPanel relationPanel = new JPanel();
		relationPanel.setBorder(new TitledBorder("Associations/Agregations"));
		relationPanel.add(relationScroller, BorderLayout.CENTER);




		/*
		 * SETUP FOR METRICS JLIST
		 */
		this.listMetrics = new JList<>();

		listMetrics.setModel(metricsDefaultModel);	
		listMetrics.addListSelectionListener(this);		

		JScrollPane metricsScroller = new JScrollPane(listMetrics);
		metricsScroller.setViewportView(listMetrics);
		metricsScroller.setPreferredSize(new Dimension(250, 200));

		/*
		 * SETUP FOR METRICS PANEL
		 */
		JPanel metricsPanel = new JPanel();
		metricsPanel.setBorder(new TitledBorder("Metrics"));
		metricsPanel.add(metricsScroller, BorderLayout.CENTER);




		/*
		 * SETUP FOR ENTIRE JPANEL
		 */
		setSize(1250,750);
		JPanel squarePanel = new JPanel();
		squarePanel.setLayout(new GridLayout(2,2));
		squarePanel.add(attributePanel);
		squarePanel.add(operationPanel);
		squarePanel.add(generalizationPanel);
		squarePanel.add(relationPanel);

		add(loadPanel, BorderLayout.NORTH);
		add(classPanel, BorderLayout.WEST);
		add(squarePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		add(metricsPanel, BorderLayout.EAST);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void loadGUIElements( ) {

		ArrayList<Classe> classes = Model.getListClass();

		/*
		 * SETUP FOR CLASSES JLIST
		 */
		classes.stream().forEach((item) -> {
			classDefaultModel.addElement(item.getClassName());
		});

		listClasses.repaint();
	}

	public String showPopUp(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int result = fileChooser.showOpenDialog(this);

		if(result == JFileChooser.APPROVE_OPTION){
			File selectedFile = fileChooser.getSelectedFile();
			file_path = selectedFile.getAbsolutePath();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			FileNameLabel.setText(selectedFile.getName());
		}
		else if (result == JFileChooser.CANCEL_OPTION){
			file_path = "";
			System.out.println("Cancel was selected");

		}

		return file_path;
	}


	public void updateMetricList(){
		metricsDefaultModel.clear();

		this.metric.stream().forEach((met) -> {
			metricsDefaultModel.addElement(met.printMetric());
		});
		listMetrics.repaint();

	}
	public void updateGeneralizationList(){
		generalizationsDefaultModel.clear();

		this.generalization.stream().forEach((item) -> {
			item.getChildren().stream().forEach((child) -> {
				generalizationsDefaultModel.addElement(child);
			});
		});
		listGeneralizations.repaint();
	}

	public void updateAttributeList(){
		attributesDefaultModel.clear();

		this.attribute.stream().forEach((item) -> {
			attributesDefaultModel.addElement(item.printAttribute());
		});
		listAttributes.repaint();
	}


	public void updateOperationList(){
		operationsDefaultModel.clear();

		this.operation.stream().forEach((item) -> {
			operationsDefaultModel.addElement(item.printOperation());
		});
		listOperations.repaint();
	}

	public void updateRelationList(){
		relationsDefaultModel.clear();

		this.relation.stream().forEach((rel) -> {
			relationsDefaultModel.addElement(rel.printRelation());
		});
		listRelations.repaint();
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()){
			if (e.getSource().equals(listClasses)) {
				if(listClasses.getSelectedIndex() != -1){

					programChanged = true;

					String test = listClasses.getSelectedValue();
					Classe newlyClickedClass = Model.getClassFromName(test);
					this.generalization = newlyClickedClass.getListGeneralization();
					this.attribute = newlyClickedClass.getListAttribute();
					this.operation = newlyClickedClass.getListOperation();
					this.relation = new ArrayList<>();
					this.metric = newlyClickedClass.getListMetrics();

					for(int i = 0; i<newlyClickedClass.getListAggregation().size(); i++){
						this.relation.add((Relation)newlyClickedClass.getListAggregation().get(i));
					}

					for(int i = 0; i<newlyClickedClass.getListAssociation().size(); i++){
						this.relation.add((Relation)newlyClickedClass.getListAssociation().get(i));
					}

					updateGeneralizationList();
					updateAttributeList();
					updateOperationList();
					updateRelationList();
					updateMetricList();

					listGeneralizations.clearSelection();
					listAttributes.clearSelection();
					listOperations.clearSelection();
					listRelations.clearSelection();
					descriptionTextArea.setText("");

					listMetrics.clearSelection();

					programChanged = false;
				}
			}
			else if(e.getSource().equals(listRelations)) {
				if(listClasses.getSelectedIndex() != -1 && !programChanged){

					programChanged = true;
					listGeneralizations.clearSelection();
					listAttributes.clearSelection();
					listOperations.clearSelection();
					listMetrics.clearSelection();

					String test = listRelations.getSelectedValue();
					Relation newlyClickedRelation = (Relation)relation.get(listRelations.getSelectedIndex());
					descriptionTextArea.setText(newlyClickedRelation.recreateCode());
					programChanged = false;
				}

			}

			else if(e.getSource().equals(listAttributes)) {
				if(listAttributes.getSelectedIndex() != -1 && !programChanged){

					programChanged = true;
					listGeneralizations.clearSelection();
					listRelations.clearSelection();
					listOperations.clearSelection();
					listMetrics.clearSelection();

					String test = listClasses.getSelectedValue();
					Classe clickedAttributeClass = Model.getClassFromName(test);

					descriptionTextArea.setText(clickedAttributeClass.recreateAttributeCode());
					programChanged = false;
				}

			}

			else if(e.getSource().equals(listGeneralizations)) {
				if(listGeneralizations.getSelectedIndex() != -1 && !programChanged){

					programChanged = true;
					listAttributes.clearSelection();
					listRelations.clearSelection();
					listOperations.clearSelection();
					listMetrics.clearSelection();

					String test = listClasses.getSelectedValue();
					Classe clickedAttributeClass = Model.getClassFromName(test);

					descriptionTextArea.setText(clickedAttributeClass.recreateGeneralizationCode());
					programChanged = false;
				}
			}

			else if(e.getSource().equals(listOperations)) {
				if(listOperations.getSelectedIndex() != -1 && !programChanged){

					programChanged = true;
					listAttributes.clearSelection();
					listRelations.clearSelection();
					listGeneralizations.clearSelection();
					listMetrics.clearSelection();

					String test = listClasses.getSelectedValue();
					Classe clickedAttributeClass = Model.getClassFromName(test);

					descriptionTextArea.setText(clickedAttributeClass.recreateOperationCode());
					programChanged = false;
				}
			}

			else if(e.getSource().equals(listMetrics)) {
				if(listMetrics.getSelectedIndex() != -1 && !programChanged){

					programChanged = true;
					listAttributes.clearSelection();
					listRelations.clearSelection();
					listGeneralizations.clearSelection();
					listOperations.clearSelection();
					int index = listMetrics.getSelectedIndex();

					String test = listClasses.getSelectedValue();
					Classe clickedAttributeClass = Model.getClassFromName(test);

					descriptionTextArea.setText(clickedAttributeClass.getListMetrics().get(index).getDescription());
					programChanged = false;
				}
			}
		}
	}

	public void clearAllJList(){
		classDefaultModel.clear();
		generalizationsDefaultModel.clear();
		attributesDefaultModel.clear();
		operationsDefaultModel.clear();
	}


	public static void writeMetricsToFile(){
		String result = ",";
		if(Model.getListClass().size()>0){
			for(int k=0; k<Model.getListClass().get(0).getListMetrics().size(); k++){
				result+= Model.getListClass().get(0).getListMetrics().get(k).getMetricName();
				if(k!= Model.getListClass().get(0).getListMetrics().size()-1)
					result+=",";
				else result+="\n";
			}
		}


		for(int i=0; i<Model.getListClass().size(); i++){
			result += Model.getListClass().get(i).getClassName() +",";
			for(int j=0; j<Model.getListClass().get(i).getListMetrics().size(); j++){
				ArrayList<Metrics> classMetricList = Model.getListClass().get(i).getListMetrics();

				result+= classMetricList.get(j).getMetricValue();
				if(j!= classMetricList.size() -1)
					result+=",";
				else result+="\n";
			}

		}

		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter( new FileWriter( "MetricsOutput.csv"));
		    writer.write( result);

		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		System.out.println(result);

	}




	public static void loadMetrics(Model mod){
		for(int i=0; i<mod.getListClass().size(); i++)
		{
			Classe c = mod.getListClass().get(i);
			c.addMetric(new ANA(c));
			c.addMetric(new NOM(c));
			c.addMetric(new NOA(c));
			c.addMetric(new ITC(c));
			c.addMetric(new ETC(c));
			c.addMetric(new CAC(c));
			c.addMetric(new DIT(c));
			c.addMetric(new CLD(c));
			c.addMetric(new NOC(c));
			c.addMetric(new NOD(c));
		}
	}
	//for the button click!
	@Override
	public void actionPerformed(ActionEvent arg0) {
		listClasses.clearSelection();
		clearAllJList();
		String filename = showPopUp();
		if(!filename.equals("")){
			String extension = filename.substring(filename.length()-3, filename.length());
			System.out.println("extension is : " + extension);

			if(!filename.equals("") && ((extension.equals("txt"))||(extension.equals("ucd")))){
				this.mod = Parser.launch(filename, this);
				loadMetrics(this.mod);
				this.loadGUIElements();	
				writeMetricsToFile();

			}
			else{
				JOptionPane.showMessageDialog(this, "Extension du fichier choisi est invalide, veuillez en choisir un autre.");
			}	
		}
	}
}
