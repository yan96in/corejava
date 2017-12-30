package swingWoker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/**
 * this program demonstrates a worker thread that runs a potentially time-consuming task;
 * Created by the best programmer on 2017/10/24.
 * 从善如登，从恶如崩。
 */
public class SwingWorkerTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new SwingWorkerFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SwingWorkerFrame extends JFrame{
    private JFileChooser chooser;
    private JTextArea textArea;
    private JLabel statusLine;
    private JMenuItem openItem;
    private JMenuItem cancelItem;
    private SwingWorker<StringBuilder,ProgressData> textReader;
    private class ProgressData{
        public int number;
        public String line;
    }

    private class TextReader extends SwingWorker<StringBuilder,ProgressData>{
        private File file;
        private StringBuilder text=new StringBuilder();

        public TextReader(File file) {
            this.file = file;
        }
        //the following method executes in the worker thread; it doesn't touch Swing components.
        @Override
        protected StringBuilder doInBackground() throws Exception {
            int lineNumber=0;
            try(Scanner in=new Scanner(new FileInputStream(file),"UTF-8")){
                while (in.hasNextLine()){
                    String line=in.nextLine();
                    lineNumber++;
                    text.append(line).append("\n");
                    ProgressData data=new ProgressData();
                    data.number=lineNumber;
                    data.line=line;
                    /**
                     * Sends data chunks to the {@link #process} method. This method is to be
                     * used from inside the {@code doInBackground} method to deliver
                     * intermediate results
                     * for processing on the <i>Event Dispatch Thread</i> inside the
                     * {@code process} method.
                     *
                     * <p>
                     * Because the {@code process} method is invoked asynchronously on
                     * the <i>Event Dispatch Thread</i>
                     * multiple invocations to the {@code publish} method
                     * might occur before the {@code process} method is executed. For
                     * performance purposes all these invocations are coalesced into one
                     * invocation with concatenated arguments.
                     *
                     * <p>
                     * For example:
                     *
                     * <pre>
                     * publish(&quot;1&quot;);
                     * publish(&quot;2&quot;, &quot;3&quot;);
                     * publish(&quot;4&quot;, &quot;5&quot;, &quot;6&quot;);
                     * </pre>
                     *
                     * might result in:
                     *
                     * <pre>
                     * process(&quot;1&quot;, &quot;2&quot;, &quot;3&quot;, &quot;4&quot;, &quot;5&quot;, &quot;6&quot;)
                     * </pre>
                     *
                     * <p>
                     * <b>Sample Usage</b>. This code snippet loads some tabular data and
                     * updates {@code DefaultTableModel} with it. Note that it safe to mutate
                     * the tableModel from inside the {@code process} method because it is
                     * invoked on the <i>Event Dispatch Thread</i>.
                     *
                     * <pre>
                     * class TableSwingWorker extends
                     *         SwingWorker&lt;DefaultTableModel, Object[]&gt; {
                     *     private final DefaultTableModel tableModel;
                     *
                     *     public TableSwingWorker(DefaultTableModel tableModel) {
                     *         this.tableModel = tableModel;
                     *     }
                     *
                     *     {@code @Override}
                     *     protected DefaultTableModel doInBackground() throws Exception {
                     *         for (Object[] row = loadData();
                     *                  ! isCancelled() &amp;&amp; row != null;
                     *                  row = loadData()) {
                     *             publish((Object[]) row);
                     *         }
                     *         return tableModel;
                     *     }
                     *
                     *     {@code @Override}
                     *     protected void process(List&lt;Object[]&gt; chunks) {
                     *         for (Object[] row : chunks) {
                     *             tableModel.addRow(row);
                     *         }
                     *     }
                     * }
                     * </pre>
                     *
                     * @param chunks intermediate results to process
                     *
                     * @see #process
                     *
                     */
                    publish(data);
                    Thread.sleep(1);
                }
            }
            return text;
        }
        // the following methods execute in the event dispatch thread.

        @Override
        protected void process(List<ProgressData> data) {
            if(isCancelled())return;
            StringBuilder b=new StringBuilder();
            statusLine.setText(""+data.get(data.size()-1).number);
            for(ProgressData d:data)
                b.append(d.line).append("\n");
            textArea.append(b.toString());
        }

        @Override
        protected void done() {
            try{
                StringBuilder result=get();
                textArea.setText(result.toString());
                statusLine.setText("Done");
            }catch (InterruptedException ex){}
            catch (CancellationException ex){
                textArea.setText("");
                statusLine.setText("Cancelled");
            }catch (ExecutionException  ex){
                statusLine.setText(""+ex.getCause());
            }
            cancelItem.setEnabled(false);
            openItem.setEnabled(true);
        }
    }
}

