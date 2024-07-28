package test;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Settings extends JFrame {

    private JButton btnUp;
    private JButton btnDown;
    private JButton btnOnMus;
    private JButton btnOffMus;
    private JButton btnOnDark;
    private JButton btnOffDark;
    private JButton btnBack;

    private static Clip clip;
    private static FloatControl volumeControl;
    private static boolean isMusicPlaying = false;
    private static String musicFilePath = "E:\\DoAn\\Java\\TicTacToe\\music.wav";
    private static float volume = 0.5f; // Initial volume (0.0 to 1.0)

    public Settings() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Settings");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3, 10, 10));  // 4 rows, 3 columns, 10px padding

        JLabel lblVolume = new JLabel("Volume");
        JLabel lblBackgroundMusic = new JLabel("Background music");
        JLabel lblDarkMode = new JLabel("Dark mode");

        btnUp = new JButton("UP");
        btnDown = new JButton("DOWN");
        btnOnMus = new JButton("ON");
        btnOffMus = new JButton("OFF");
        btnOnDark = new JButton("ON");
        btnOffDark = new JButton("OFF");
        btnBack = new JButton("BACK");

        panel.add(lblVolume);
        panel.add(btnUp);
        panel.add(btnDown);

        panel.add(lblBackgroundMusic);
        panel.add(btnOnMus);
        panel.add(btnOffMus);

        panel.add(lblDarkMode);
        panel.add(btnOnDark);
        panel.add(btnOffDark);

        panel.add(new JLabel());  // Empty label to create space
        panel.add(btnBack);

        add(panel);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chuyển về class Home
                new Home().setVisible(true);
                dispose();
            }
        });

        btnOnMus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playMusic(musicFilePath);
            }
        });

        btnOffMus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopMusic();
            }
        });

        btnUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseVolume();
            }
        });

        btnDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseVolume();
            }
        });

        btnOnDark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableDarkMode();
            }
        });

        btnOffDark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disableDarkMode();
            }
        });

        if (isMusicPlaying) {
            btnOnMus.setEnabled(false);
            btnOffMus.setEnabled(true);
        } else {
            btnOnMus.setEnabled(true);
            btnOffMus.setEnabled(false);
        }
    }

    private void playMusic(String filePath) {
        stopMusic();  // Dừng nhạc hiện tại nếu đang phát
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            setVolume(volume);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Vòng lặp liên tục
            clip.start();
            isMusicPlaying = true;
            btnOnMus.setEnabled(false);
            btnOffMus.setEnabled(true);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    private void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        isMusicPlaying = false;
        btnOnMus.setEnabled(true);
        btnOffMus.setEnabled(false);
    }

    private void setVolume(float volume) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            // Ensure the volume value is within the allowable range
            float value = min + (max - min) * Math.max(0, Math.min(1, volume));
            volumeControl.setValue(value);
        }
    }

    private void increaseVolume() {
        if (volume < 1.0f) {
            volume += 0.1f;
            setVolume(volume);
        }
    }

    private void decreaseVolume() {
        if (volume > 0.0f) {
            volume -= 0.1f;
            setVolume(volume);
        }
    }

    private void enableDarkMode() {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        UIManager.getLookAndFeelDefaults().put("Button.arc", 999);
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }

    private void disableDarkMode() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        javax.swing.SwingUtilities.updateComponentTreeUI(this);
    }
}
