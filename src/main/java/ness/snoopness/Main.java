package ness.snoopness;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;

public class Main
{
    private DataBindingContext m_bindingContext;

    protected Shell shlSnoopness;
    private Table slotTable;
    private Table table;
    private Snoopness snoopness = new Snoopness();

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args)
    {
        Display display = Display.getDefault();
        Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
            @Override
            public void run() {
                try {
                    Main window = new Main();
                    window.open();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Open the window.
     */
    public void open()
    {
        snoopness.setMain(this);

        Display display = Display.getDefault();
        createContents();
        shlSnoopness.open();
        shlSnoopness.layout();

        snoopness.start();

        while (!shlSnoopness.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents()
    {
        shlSnoopness = new Shell();
        shlSnoopness.setSize(1024, 768);
        shlSnoopness.setText("SnoopNess");
        shlSnoopness.setLayout(new BorderLayout(0, 0));

        TabFolder mainTabs = new TabFolder(shlSnoopness, SWT.NONE);

        TabItem tbtmSetup = new TabItem(mainTabs, SWT.NONE);
        tbtmSetup.setText("Configure");

        Composite composite_1 = new Composite(mainTabs, SWT.NONE);
        tbtmSetup.setControl(composite_1);
        composite_1.setLayout(new GridLayout(3, false));

        Combo combo = new Combo(composite_1, SWT.NONE);
        combo.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1));
        combo.setItems(new String[] {"gonsole.staging.nessops.net", "gonsole.production.nessops.net"});
        combo.setText("gonsole.staging.nessops.net");

        slotTable = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
        GridData gd_slotTable = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
        gd_slotTable.widthHint = 346;
        gd_slotTable.heightHint = 436;
        slotTable.setLayoutData(gd_slotTable);
        slotTable.setHeaderVisible(true);
        slotTable.setLinesVisible(true);

        TableColumn tblclmnSlot = new TableColumn(slotTable, SWT.NONE);
        tblclmnSlot.setWidth(100);
        tblclmnSlot.setText("Slot");

        TableColumn tblclmnHost = new TableColumn(slotTable, SWT.NONE);
        tblclmnHost.setWidth(100);
        tblclmnHost.setText("Host");

        TableColumn tblclmnServiceType = new TableColumn(slotTable, SWT.NONE);
        tblclmnServiceType.setWidth(144);
        tblclmnServiceType.setText("Service Type");

        TabFolder selectTabs = new TabFolder(composite_1, SWT.NONE);
        selectTabs.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

        TabItem tbtmServiceType = new TabItem(selectTabs, SWT.NONE);
        tbtmServiceType.setText("Service Type");

        Tree tree = new Tree(selectTabs, SWT.BORDER);
        tbtmServiceType.setControl(tree);

        TreeColumn trclmnService = new TreeColumn(tree, SWT.NONE);
        trclmnService.setWidth(100);
        trclmnService.setText("Service Type");

        TabItem tbtmHost = new TabItem(selectTabs, SWT.NONE);
        tbtmHost.setText("Host");

        table = new Table(selectTabs, SWT.BORDER | SWT.FULL_SELECTION);
        tbtmHost.setControl(table);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn tblclmnSlot_1 = new TableColumn(table, SWT.NONE);
        tblclmnSlot_1.setWidth(400);
        tblclmnSlot_1.setText("Host");

        TabItem tbtmCapture = new TabItem(mainTabs, SWT.NONE);
        tbtmCapture.setText("Capture");

        Composite composite = new Composite(mainTabs, SWT.NONE);
        tbtmCapture.setControl(composite);
        composite.setLayout(new BorderLayout(0, 0));
        m_bindingContext = initDataBindings();

    }

    public Table getSlotTable() {
        return slotTable;
    }
    protected DataBindingContext initDataBindings() {
        DataBindingContext bindingContext = new DataBindingContext();
        //
        IObservableValue observeSingleSelectionIndexSlotTableObserveWidget = WidgetProperties.singleSelectionIndex().observe(slotTable);
        IObservableValue slotListSnoopnessObserveValue = PojoProperties.value("slotList").observe(snoopness);
        bindingContext.bindValue(observeSingleSelectionIndexSlotTableObserveWidget, slotListSnoopnessObserveValue, null, null);
        //
        return bindingContext;
    }
}
