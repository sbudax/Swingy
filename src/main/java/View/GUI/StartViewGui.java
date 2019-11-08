package View.GUI;

import Controller.StartGame;
import View.Console.StartViewConsole;
import View.Interface.SelectHeroView;
import Main.*;
import View.Interface.StartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartViewGui extends JPanel implements SelectHeroView, StartView{
    private JButton createHeroButton = new JButton("Create Hero");
    private JButton selectHeroButton = new JButton("Select Hero");
    private JButton switchViewButton = new JButton("Switch to console");

    private StartGame controller;

    @Override
    public void start() {
        controller = new StartGame(this);

        buildUI();
    }

    @Override
    public void updateInfo(String info) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void openGame() {

    }

    private void buildUI() {
        Main.getFrame().setTitle("Start");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.add(createHeroButton, gbc);
        this.add(selectHeroButton, gbc);
        this.add(switchViewButton, gbc);

        this.setVisible(true);
        Main.getFrame().setContentPane(this);
        Main.getFrame().revalidate();
        Main.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateHero();
            }
        });
        selectHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectHero();
            }
        });
        switchViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitch();
            }
        });
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        new NewHeroGui().start();
    }

    @Override
    public void switchView() {
        Main.hideFrame();
        new StartViewConsole().start();
    }

    @Override
    public void openSelectHero() {
        this.setVisible(false);
        new SelectHeroViewGui().start();
    }
}
