package fi.leif.java.screenshot.decoder.selection;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import fi.leif.java.screenshot.decoder.Screenshot;

public class FillAreaSelection {

    private Screenshot screenshot;
    private BufferedImage background;
    
    public FillAreaSelection(Screenshot screenshot) {
        
        this.screenshot = screenshot;
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Decoder");
                frame.setUndecorated(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.add(new BackgroundPane());
                frame.setVisible(true);
                frame.setCursor( Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR) );
            }
        });
    }

    public class BackgroundPane extends JPanel {

        private Point mouseAnchor;
        private Point dragPoint;

        private SelectionPane selectionPane;

        public Rectangle getBounds() {
            return this.selectionPane.getBounds();
        }
        public BackgroundPane() {
            selectionPane = new SelectionPane();
            try {
                Robot bot = new Robot();
                background = bot.createScreenCapture(getScreenViewableBounds());
            } catch (AWTException ex) {
                Logger.getLogger(FillAreaSelection.class.getName()).log(Level.SEVERE, null, ex);
            }

            selectionPane = new SelectionPane();
            setLayout(null);
            add(selectionPane);

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
            g2d.drawImage(background, 0, 0, this);
            g2d.dispose();
        }

    }

    public class SelectionPane extends JPanel {

        public SelectionPane() {
            setOpaque(false);
            
            // Quit app if escape is pressed
            this.addKeyListener( new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        System.exit(0);
                    }
                }
            });  
            this.setFocusable(true);
            
            // Decode button
            JButton button = new JButton("Decode");
            button.setCursor( Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR) );
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
                    Rectangle r = getBounds();
                    BufferedImage img = background.getSubimage((int)r.getMinX(), (int)r.getMinY(), 
                            (int)r.getWidth(), (int)r.getHeight() );
                    screenshot.handleImage( img);
                    
                }
            });
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.SOUTHEAST;
            add(button, gbc);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(new Color(100, 100, 255, 64));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            float dash1[] = {5.0f};
            BasicStroke dashed =
                            new BasicStroke(1.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            1.0f, dash1, 0.0f);
            g2d.setColor(new Color(100, 100, 255, 64));
            g2d.setStroke(dashed);
            g2d.drawRect(0, 0, getWidth()-1, getHeight()-1);
            g2d.dispose();
        }

    }

    public static Rectangle getScreenViewableBounds() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        return getScreenViewableBounds(gd);
    }

    public static Rectangle getScreenViewableBounds(GraphicsDevice gd) {
        Rectangle bounds = new Rectangle(0, 0, 0, 0);
        if (gd != null) {
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            bounds = gc.getBounds();

            Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

            bounds.x += insets.left;
            bounds.y += insets.top;
            bounds.width -= (insets.left + insets.right);
            bounds.height -= (insets.top + insets.bottom);
        }
        return bounds;
    }
}