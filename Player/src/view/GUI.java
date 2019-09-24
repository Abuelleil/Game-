package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.*;
import controller.GameController;
import model.pieces.Piece;

public class GUI extends JFrame {
    private GameController GC;
    private JLabel background;
    private JPanel start;
    private JPanel game;
    private JPanel name;
    private JTextField pl1;
    private JTextField pl2;
    private JFrame error;

    public JTextField getPl1() {
        return pl1;
    }

    public JTextField getPl2() {
        return pl2;
    }

    public GUI(GameController GC) {
        this.GC = GC;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.background = new JLabel(new ImageIcon("./media/start.png"));
        // this.setUndecorated(true);
        this.setTitle("7lw el mashrou3 da");
        this.background.setLayout(null);
        this.add(background);
        this.background.setBounds(0, 0, 1366, 768);
        this.background.setOpaque(false);
        this.background.setVisible(true);
        this.setSize(1366, 768);
        this.setFocusable(true);
        this.setVisible(true);
        startview();
    }

    public void endgame() {
        background.removeAll();
        JPanel screen = new JPanel();
        JLabel message = new JLabel("YOU HAVE WON");
        message.setForeground(Color.WHITE);
        screen.add(message);
        message.setBounds(600, 350, 166, 68);
        message.setVisible(true);
        message.setOpaque(false);
        JButton play = new JButton("play again");
        play.addActionListener(GC);
        play.setActionCommand("NEW GAME yasta");
        JButton end = new JButton("end game");
        end.addActionListener(GC);
        end.setActionCommand("close window");
        screen.setLayout(null);
        screen.add(end);
        end.setBounds(400, 450, 200, 70);
        screen.add(play);
        play.setBounds(750, 450, 200, 70);
        play.setVisible(true);
        end.setVisible(true);
        background.add(screen);
        screen.setBounds(0, 0, 1366, 768);
        screen.setVisible(true);
        screen.setOpaque(false);
        this.paintComponents(getGraphics());
    }


    public void ending() {
        JPanel credits = new JPanel();
        JButton x = new JButton("click for a surprise");
        JPanel thanks = new JPanel();
        credits.add(x);
        x.addActionListener(GC);
        x.setActionCommand("click for a surprise");
        background.add(credits);
        x.setVisible(true);
        credits.setVisible(true);
        credits.setOpaque(false);
        x.setOpaque(false);
        revalidate();
    }

    public void startview() {
        start = new JPanel();
        start.setLayout(new FlowLayout());
        JButton B = new JButton("NEW GAME yasta");
        B.setActionCommand("NEW GAME yasta");
        B.addActionListener(GC);
        start.add(B);
        background.add(start);
        start.setBounds(567, 100, 200, 70);
        start.setVisible(true);
        B.setVisible(true);
        start.setOpaque(false);
        this.paintComponents(getGraphics());
    }

    public void nameselect() {
        background.removeAll();
        name = new JPanel();
        name.setLayout(null);
        pl1 = new JTextField();
        pl2 = new JTextField();
        JButton next = new JButton("next");
        next.setActionCommand("next");
        next.addActionListener(GC);
        name.add(next);
        name.add(pl1);
        name.add(pl2);
        next.setBounds(550, 100, 200, 70);
        pl1.setBounds(100, 330, 200, 30);
        pl2.setBounds(1066, 330, 200, 30);
        JLabel p1name = new JLabel("NIFELHEIM");
        JLabel p2name = new JLabel("LUCIS");
        name.add(p1name);
        name.add(p2name);
        background.add(name);
        name.setBounds(0, 0, 1366, 768);
        p1name.setBounds(100, 300, 200, 30);
        p2name.setBounds(1066, 300, 200, 30);
        p1name.setForeground(Color.WHITE);
        p2name.setForeground(Color.WHITE);
        name.setVisible(true);
        pl1.setVisible(true);
        pl2.setVisible(true);
        p1name.setVisible(true);
        p2name.setVisible(true);
        name.setOpaque(false);
        this.paintComponents(getGraphics());
    }

    public void Game() {
        background.removeAll();
        game = new JPanel();
        game.setLayout(new GridLayout(7, 6));
        JPanel command = new JPanel();
        command.setLayout(new FlowLayout());
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (GC.getGame().getCellAt(i, j).getPiece() == null) {
                    BB B = new BB("", i, j);
                    game.add(B);
                    B.setBackground(Color.BLACK);
                    B.addActionListener(GC);
                    B.setActionCommand("Empty");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Super")) {
                    BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(),
                            GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                            + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Ranged")) {
                    if (GC.getGame().getCellAt(i, j).getPiece().getOwner().equals(GC.getGame().getPlayer1())) {
                        BB B = new BB(new ImageIcon("./media/prompto.png"), GC.getGame().getCellAt(i, j).getPiece());
                        B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                                + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                        game.add(B);
                        B.addActionListener(GC);
                        B.setBackground(Color.WHITE);
                        B.setActionCommand("Piece");
                    } else {
                        BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(), GC.getGame().getCellAt(i, j).getPiece());
                        B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                                + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                        game.add(B);
                        B.addActionListener(GC);
                        B.setBackground(Color.WHITE);
                        B.setActionCommand("Piece");
                    }
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Medic")) {
                    BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(),
                            GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                            + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Speedster")) {
                    BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(),
                            GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                            + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                    game.add(B);
                    B.addActionListener(GC);
                    B.setBackground(Color.WHITE);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Tech")) {
                    BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(),
                            GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                            + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("Armored")) {
                    BB B = new BB(GC.getGame().getCellAt(i, j).getPiece().getName(),
                            GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s "
                            + GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName());
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("SideKickP1")) {
                    BB B = new BB("SideKickP1", GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s Sidekick");
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                } else if (GC.getGame().getCellAt(i, j).getPiece().getClass().getSimpleName().equals("SideKickP2")) {
                    BB B = new BB("SideKickP2", GC.getGame().getCellAt(i, j).getPiece());
                    B.setToolTipText(GC.getGame().getCellAt(i, j).getPiece().getOwner().getName() + "'s Sidekick");
                    game.add(B);
                    B.setBackground(Color.WHITE);
                    B.addActionListener(GC);
                    B.setActionCommand("Piece");
                }
            }
        }
        command.setVisible(true);
        background.add(command);
        command.setBounds(1160, 350, 150, 68);
        command.setOpaque(false);
        background.add(game);
        game.setBounds(333, 100, 650, 568);
        game.setVisible(true);
        game.setOpaque(false);
        Moveset();
        ending();
        playerscreen();
        playerturn();
        panelView();
        // this.paintComponents(getGraphics());
        this.revalidate();
    }

    public void dispError(Exception e) {
        error = new JFrame();
        error.setTitle("Error ya m3lm");
        error.setSize(300, 300);
        error.setLayout(new BorderLayout());
        JLabel message = new JLabel(e.getMessage());
        error.add(message);
        message.setVisible(true);
        error.setVisible(true);
    }

    public void techOptions() {
        JPanel options = new JPanel();
        options.setLayout(new FlowLayout());
        JButton tp = new JButton("Teleport");
        JButton power = new JButton("Change Power");
        tp.setActionCommand("teleport");
        tp.addActionListener(GC);
        options.add(tp);
        options.add(power);
        options.setOpaque(false);
        background.add(options);
        options.setBounds(1200, 10, 166, 100);
        options.setVisible(true);
        this.revalidate();
    }

    public void playerscreen() {
        JPanel playerscreen = new JPanel();
        playerscreen.setLayout(null);
        JLabel p1name = new JLabel(GC.getGame().getPlayer1().getName());
        JLabel p2name = new JLabel(GC.getGame().getPlayer2().getName());
//		JPanel p1payload= new JPanel();
//		JLabel payload= new JLabel();
//		p1payload.setLayout(new FlowLayout());
//		JPanel p2payload= new JPanel();
//		p2payload.setLayout(new FlowLayout());
        JPanel p1dead = new JPanel();
        JPanel p2dead = new JPanel();
        p1dead.setLayout(new GridLayout(4, 3));
        p2dead.setLayout(new GridLayout(4, 3));
        for (Piece piece : GC.getGame().getPlayer1().getDeadCharacters()) {
            DCB dcb = new DCB(piece.getName(), piece);
            p1dead.add(dcb);
            dcb.addActionListener(GC);
            dcb.setActionCommand("dead");
        }
        for (Piece piece : GC.getGame().getPlayer2().getDeadCharacters()) {
            DCB dcb = new DCB(piece.getName(), piece);
            p2dead.add(dcb);
            dcb.addActionListener(GC);
            dcb.setActionCommand("dead");
        }
        playerscreen.add(p1name);
        p1name.setFont(new Font("Serif", Font.ITALIC, 24));
        p1name.setForeground(Color.WHITE);
        p2name.setForeground(Color.WHITE);
        playerscreen.add(p2name);
        p2name.setFont(new Font("Serif", Font.ITALIC, 24));
        playerscreen.add(p1dead);
        playerscreen.add(p2dead);
        p1name.setBounds(30, 100, 300, 30);
        p2name.setBounds(30, 400, 300, 30);
        p1dead.setBounds(30, 130, 300, 270);
        p2dead.setBounds(30, 430, 300, 270);
        p1name.setVisible(true);
        p1name.setOpaque(false);
        p2name.setVisible(true);
        p2name.setOpaque(false);
        p1dead.setVisible(true);
        p1dead.setOpaque(false);
        p2dead.setVisible(true);
        p2dead.setOpaque(false);
        background.add(playerscreen);
        playerscreen.setBounds(0, 0, 330, 768);
        playerscreen.setVisible(true);
        playerscreen.setOpaque(false);
    }

    public void playerturn() {
        JPanel play = new JPanel();
        JLabel L = new JLabel("it is Player " + GC.getGame().getCurrentPlayer().getName() + "'s Turn");
        play.setLayout(new FlowLayout());
        play.add(L);
        L.setForeground(Color.WHITE);
        background.add(play);
        play.setBounds(480, 30, 400, 70);// Location of the statement on the board
        L.setFont(new Font("Serif", Font.ITALIC, 36));
        //play.setSize(480,480);
        play.setVisible(true);
        play.setOpaque(false);
    }

    public void Moveset() {
        JPanel babuttons = new JPanel();
        babuttons.setLayout(new GridLayout(3, 3));
        JButton usepower = new JButton("power");
        JButton UP = new JButton("Up");
        JButton DOWN = new JButton("Down");
        JButton RIGHT = new JButton("Right");
        JButton LEFT = new JButton("Left");
        JButton UPLEFT = new JButton("UpLeft");
        JButton UPRIGHT = new JButton("UpRight");
        JButton DOWNLEFT = new JButton("DownLeft");
        JButton DOWNRIGHT = new JButton("DownRight");
        babuttons.add(UPLEFT);
        babuttons.add(UP);
        babuttons.add(UPRIGHT);
        babuttons.add(LEFT);
        babuttons.add(usepower);
        babuttons.add(RIGHT);
        babuttons.add(DOWNLEFT);
        babuttons.add(DOWN);
        babuttons.add(DOWNRIGHT);
        UP.setActionCommand("UP");
        UP.addActionListener(GC);
        DOWN.setActionCommand("DOWN");
        DOWN.addActionListener(GC);
        LEFT.setActionCommand("LEFT");
        LEFT.addActionListener(GC);
        RIGHT.setActionCommand("RIGHT");
        RIGHT.addActionListener(GC);
        UPLEFT.setActionCommand("UPLEFT");
        UPLEFT.addActionListener(GC);
        UPRIGHT.setActionCommand("UPRIGHT");
        UPRIGHT.addActionListener(GC);
        DOWNLEFT.setActionCommand("DOWNLEFT");
        DOWNLEFT.addActionListener(GC);
        DOWNRIGHT.setActionCommand("DOWNRIGHT");
        DOWNRIGHT.addActionListener(GC);
        usepower.setActionCommand("power");
        usepower.addActionListener(GC);
        background.add(babuttons);
        babuttons.setVisible(true);
        babuttons.setBounds(983, 100, 300, 568);
    }

    public void panelView() {
        JLabel p1 = new JLabel();
        p1.setBackground(Color.GREEN);
        JLabel p2 = new JLabel();
        p2.setBackground(Color.RED);
        background.add(p1);
        background.add(p2);
        if (GC.getGame().getPlayer1().getPayloadPos() == 1) {
            p1.setBounds(00, 380, 60, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        } else if (GC.getGame().getPlayer1().getPayloadPos() == 2) {
            p1.setBounds(00, 380, 120, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        } else if (GC.getGame().getPlayer1().getPayloadPos() == 3) {
            p1.setBounds(000, 400, 180, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        } else if (GC.getGame().getPlayer1().getPayloadPos() == 4) {
            p1.setBounds(000, 400, 240, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        } else if (GC.getGame().getPlayer1().getPayloadPos() == 5) {
            p1.setBounds(000, 400, 300, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        } else if (GC.getGame().getPlayer1().getPayloadPos() >= 6) {
            p1.setBounds(000, 400, 360, 30);
            p1.setVisible(true);
            p1.setOpaque(true);
        }

        if (GC.getGame().getPlayer2().getPayloadPos() == 1) {
            p2.setBounds(000, 650, 60, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        } else if (GC.getGame().getPlayer2().getPayloadPos() == 2) {
            p2.setBounds(00, 650, 120, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        } else if (GC.getGame().getPlayer2().getPayloadPos() == 3) {
            p2.setBounds(00, 650, 180, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        } else if (GC.getGame().getPlayer2().getPayloadPos() == 4) {
            p2.setBounds(00, 650, 240, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        } else if (GC.getGame().getPlayer2().getPayloadPos() == 5) {
            p2.setBounds(00, 650, 300, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        } else if (GC.getGame().getPlayer2().getPayloadPos() >= 6) {
            p2.setBounds(00, 650, 360, 30);
            p2.setVisible(true);
            p2.setOpaque(true);
        }

    }

}