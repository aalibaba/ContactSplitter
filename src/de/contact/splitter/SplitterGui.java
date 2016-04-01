package de.contact.splitter;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CLabel;

public class SplitterGui {

	protected Shell shell;
	private Text tKontakt;
	private Text tAnrede;
	private Text tTitel;
	private Text tVorname;
	private Text tNachname;
	
	private List<String> prefixList;
	private List<String> titelList;
	private Text tAddTitel;
	private Text tAddPrefix;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			SplitterGui window = new SplitterGui();
			
			window.addToTitelList("Dr\\. rer\\. nat\\. ");
			window.addToTitelList("Dipl\\. Ing\\. ");
			window.addToTitelList("Prof\\. ");
			window.addToTitelList("Dr\\. ");
			
			window.addToPrefixList(" van der ");
			window.addToPrefixList(" van de ");
			window.addToPrefixList(" von der ");
			window.addToPrefixList(" von und zu ");
			window.addToPrefixList(" von ");
			window.addToPrefixList(" van ");
			window.addToPrefixList(" zu ");
			window.addToPrefixList(" de ");
			
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void addToPrefixList(String prefix){
		if(prefixList == null){
			prefixList = new ArrayList<String>();
		}
		prefixList.add(prefix);
	}
	
	void addToTitelList(String anrede){
		if(titelList == null){
			titelList = new ArrayList<String>();
		}
		titelList.add(anrede);
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 454);
		shell.setText("Contact Splitter");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 414, 396);
		
		TabItem tbtmNeuerKontakt = new TabItem(tabFolder, SWT.NONE);
		tbtmNeuerKontakt.setText("Neuer Kontakt");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmNeuerKontakt.setControl(composite);
		
		Label lblKontakt = new Label(composite, SWT.NONE);
		lblKontakt.setBounds(10, 10, 55, 15);
		lblKontakt.setText("Kontakt");
		
		tKontakt = new Text(composite, SWT.BORDER);
		tKontakt.setBounds(10, 31, 386, 21);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 69, 386, 2);
		
		Label lblBriefanrede = new Label(composite, SWT.NONE);
		lblBriefanrede.setBounds(10, 89, 80, 15);
		lblBriefanrede.setText("Briefanrede");
		
		Label lblAnrede = new Label(composite, SWT.NONE);
		lblAnrede.setBounds(10, 130, 55, 15);
		lblAnrede.setText("Anrede");
		
		Label lblTitel = new Label(composite, SWT.NONE);
		lblTitel.setBounds(10, 173, 55, 15);
		lblTitel.setText("Titel");
		
		Label lblVorname = new Label(composite, SWT.NONE);
		lblVorname.setBounds(10, 219, 55, 15);
		lblVorname.setText("Vorname");
		
		Label lblNachname = new Label(composite, SWT.NONE);
		lblNachname.setBounds(10, 266, 67, 15);
		lblNachname.setText("Nachname");
		
		Label lBriefanrede = new Label(composite, SWT.NONE);
		lBriefanrede.setBounds(112, 101, 197, 15);
		
		tAnrede = new Text(composite, SWT.BORDER);
		tAnrede.setBounds(112, 130, 197, 21);
		
		tTitel = new Text(composite, SWT.BORDER);
		tTitel.setBounds(112, 173, 197, 21);
		
		tVorname = new Text(composite, SWT.BORDER);
		tVorname.setBounds(112, 219, 197, 21);
		
		tNachname = new Text(composite, SWT.BORDER);
		tNachname.setBounds(112, 266, 197, 21);
		
		Button btnSplitt = new Button(composite, SWT.NONE);
		btnSplitt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ContactSplitter cSplitter = new ContactSplitter(prefixList, titelList);
				Contact splittetContact = cSplitter.split(tKontakt.getText());
				tAnrede.setText(splittetContact.getAnrede());
				tTitel.setText(splittetContact.getTitel());
				tVorname.setText(splittetContact.getVorname());
				tNachname.setText(splittetContact.getNachname());
				lBriefanrede.setText(splittetContact.getBriefanrede());
				
			}
		});
		btnSplitt.setBounds(279, 317, 75, 25);
		btnSplitt.setText("Splitt!");
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(42, 317, 75, 25);
		
		TabItem tbtmTitelHinzufgen = new TabItem(tabFolder, SWT.NONE);
		tbtmTitelHinzufgen.setText("Titel hinzuf\u00FCgen");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmTitelHinzufgen.setControl(composite_1);
		
		org.eclipse.swt.widgets.List listTitel = new org.eclipse.swt.widgets.List(composite_1, SWT.BORDER);
		listTitel.setItems(titelList.toArray(new String[titelList.size()]));
		listTitel.setBounds(10, 10, 170, 280);
		
		Label lblTitelZurListe = new Label(composite_1, SWT.NONE);
		lblTitelZurListe.setBounds(186, 10, 179, 15);
		lblTitelZurListe.setText("Titel zur Liste hinzuf\u00FCgen");
		
		tAddTitel = new Text(composite_1, SWT.BORDER);
		tAddTitel.setBounds(186, 32, 150, 21);
		
		CLabel lblNewLabel = new CLabel(composite_1, SWT.NONE);
		lblNewLabel.setBounds(186, 59, 150, 63);
		lblNewLabel.setText("Hinweis: Um einen\n Punkt einzuf\u00FCgen\n \\. schreiben");
		
		Button btnHinzufgen = new Button(composite_1, SWT.NONE);
		btnHinzufgen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				titelList.add(tAddTitel.getText());
				listTitel.add(tAddTitel.getText());
			}
		});
		btnHinzufgen.setBounds(186, 128, 75, 25);
		btnHinzufgen.setText("Hinzuf\u00FCgen");
		
		TabItem tbtmPrfixHinzufgen = new TabItem(tabFolder, SWT.NONE);
		tbtmPrfixHinzufgen.setText("Pr\u00E4fix hinzuf\u00FCgen");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmPrfixHinzufgen.setControl(composite_2);
		
		org.eclipse.swt.widgets.List listPrefix = new org.eclipse.swt.widgets.List(composite_2, SWT.BORDER);
		listPrefix.setItems(prefixList.toArray(new String[prefixList.size()]));
		listPrefix.setBounds(10, 10, 170, 280);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setText("Titel zur Liste hinzuf\u00FCgen");
		label_1.setBounds(186, 10, 179, 15);
		
		tAddPrefix = new Text(composite_2, SWT.BORDER);
		tAddPrefix.setBounds(186, 31, 150, 21);
		
		CLabel label_2 = new CLabel(composite_2, SWT.NONE);
		label_2.setText("Hinweis: Um einen\n Punkt einzuf\u00FCgen\n \\. schreiben");
		label_2.setBounds(186, 58, 150, 63);
		
		Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				prefixList.add(tAddPrefix.getText());
				listPrefix.add(tAddPrefix.getText());
			}
		});
		button_1.setText("Hinzuf\u00FCgen");
		button_1.setBounds(186, 127, 75, 25);

	}
}
