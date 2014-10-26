package fi.leif.java.screenshot.decoder.selection;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import fi.leif.java.screenshot.decoder.Screenshot;

public class CutAreaSelection {

    private SnipItPane pane;
    protected Screenshot screenshot;
    
    public CutAreaSelection(Screenshot screenshot) {
        
        this.screenshot = screenshot;
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {}

                JFrame frame = new JFrame();
                frame.setUndecorated(true);
                frame.setBackground(new Color(0, 0, 0, 0));
                frame.setLayout(new BorderLayout());
                pane = new SnipItPane();
                frame.add(pane);
                frame.setBounds(getVirtualBounds());
                frame.setVisible(true);
                frame.setCursor( Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) );
                
                // Escape to exit listener
                frame.addKeyListener( new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            System.exit(0);
                        }
                    }
                    
                });
            }
            
        });
    }

    public class SnipItPane extends JPanel {

        private Point mouseAnchor;
        private Point dragPoint;

        private SelectionPane selectionPane;

        public SnipItPane() {
            
            setOpaque(false);
            setLayout(null);
            selectionPane = new SelectionPane();
            add(selectionPane);
            
            // Mouse event
            MouseAdapter adapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseAnchor = e.getPoint();
                    dragPoint = null;
                    selectionPane.setLocation(mouseAnchor);
                    selectionPane.setSize(0, 0);
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    dragPoint = e.getPoint();
                    int width = dragPoint.x - mouseAnchor.x;
                    int height = dragPoint.y - mouseAnchor.y;

                    int x = mouseAnchor.x;
                    int y = mouseAnchor.y;

                    if (width < 0) {
                        x = dragPoint.x;
                        width *= -1;
                    }
                    if (height < 0) {
                        y = dragPoint.y;
                        height *= -1;
                    }
                    selectionPane.setBounds(x, y, width, height);
                    selectionPane.revalidate();
                    repaint();
                }
            };
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            Rectangle bounds = new Rectangle(0, 0, getWidth(), getHeight());
            Area area = new Area(bounds);
            area.subtract(new Area(selectionPane.getBounds()));

            g2d.setColor(new Color(192, 192, 192, 64));
            g2d.fill(area);

        }
        
        public Rectangle getBounds() {
            return this.selectionPane.getBounds();
        }
    }

    public class SelectionPane extends JPanel {

        public SelectionPane() {
            setOpaque(false);
            
            // Decode button
            setLayout(new GridBagLayout());
            JButton button = new JButton("Decode");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
                    screenshot.handleSelection( getBounds() );
                }
            });
            GridBagConstraints gbc = new GridBagConstraints();
            add(button, gbc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            float dash1[] = {4.0f};
            BasicStroke dashed =
                    new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    1.0f, dash1, 0.0f);
            g2d.setColor(Color.GRAY);
            g2d.setStroke(dashed);
            g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
            g2d.dispose();
        }
    }

    public static Rectangle getVirtualBounds() {

        Rectangle bounds = new Rectangle(0, 0, 0, 0);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice lstGDs[] = ge.getScreenDevices();
        for (GraphicsDevice gd : lstGDs) {
            bounds.add(gd.getDefaultConfiguration().getBounds());
        }
        return bounds;
    }
}