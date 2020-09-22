import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Main extends JPanel{

    private int points = 0;
    private int life = 5;

    private List<Enums.EComponent> componentsList;
    private JLabel text;
    private Enums.ESushi randomly;

    public Main() {
        try {
            this.componentsList = new ArrayList<>();
            setBackground(Color.white);
            setLayout(null);
            createButtonPanel();
            createTextPanel();
            createCheckButton();
            createXButton();
        }
        catch(Exception e) {
            System.out.println("Error in Main()");
        }
    }

    private void createXButton() throws IOException {
        JLabel button = new JLabel();
        button.setIcon(new ImageIcon(ImageIO.read(new File("Sushi/x.png"))));
        button.setBounds(580,100,100,50);
        add(button);

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                componentsList.clear();
                repaint();
            }
        });



    }

    private void createCheckButton() throws IOException {
        JLabel button = new JLabel();
        button.setIcon(new ImageIcon(ImageIO.read(new File("Sushi/check.png"))));
        button.setBounds(530,50,200,50);

        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Collections.sort(componentsList);
                Collections.sort(randomly.getComponentList());

                if(componentsList.equals(randomly.getComponentList())) {
                    points++;
                    componentsList.clear();
                    draw();
                }else {
                    life--;
                    componentsList.clear();
                }
                repaint();
                EndOfGame();
            }

            private void EndOfGame() {
                if(life < 1) {
                    JOptionPane.showMessageDialog(null, "Twój wynik: " + points);
                    life = 5;
                    draw();
                    points = 0;
                    repaint();
                }
            }
        });
        add(button);
    }

    private void createTextPanel(){
        text = new JLabel();
        text.setBounds(100,35,400,80);
        Font font = new Font("Arial", Font.BOLD,35);
        text.setFont(font);
        draw();
        add(text);
    }

    private void draw() {
        int index = (int)(Math.random() * Enums.ESushi.values().length);
        randomly = Enums.ESushi.values()[index];
        text.setText(randomly.getName());
    }

    private void createButtonPanel() throws IOException {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,4));

        File[] graphics = new File ("Sushi/Skladniki/").listFiles();
        if (graphics != null) {
            for (File f : graphics) {
                JLabel button = new JLabel();

                button.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        componentsList.add(Enums.EComponent.valueOf(f.getName().substring(0, f.getName().length() - 4)));
                        repaint();
                    }
                });

                button.setIcon(new ImageIcon(ImageIO.read(f)));
                buttonPanel.add(button);
            }
        }
        else
            System.out.println("Error in ComponentList");

        buttonPanel.setBounds(0,170,800,600);
        add(buttonPanel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(770,770);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        File hearth = new File("Sushi/serce.png");

        for (int i = 0; i < life; i++) {
            try {
                g.drawImage(ImageIO.read(hearth) , (i*30) + (i+1),10,20,20,null);
            } catch (Exception e){
                System.out.println("Error in drawImage");
            }

        }
        g.setFont(new Font("Arial", Font.BOLD,20));
        g.drawString("Punkty: " + points , 570,30);


        for (int i = 0; i < componentsList.size(); i++) {
            try{
                g.drawImage(ImageIO.read(new File("Sushi/Skladniki/" + componentsList.get(i) + ".png")), 50*i,120,50,50,null);
            }catch (Exception e) {
                System.out.println("Error in draw Components");
            }

        }
    }

    public static void main(String[] args)  {
       JFrame window = new JFrame("Kreator Sushi");
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setVisible(true);
       window.add(new Main());
       window.pack();

    }
}
