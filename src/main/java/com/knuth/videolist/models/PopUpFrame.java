package com.knuth.videolist.models;

import javax.swing.JOptionPane;

public class PopUpFrame extends javax.swing.JFrame {

    public PopUpFrame() {
        frameInit();
        this.setLocationRelativeTo(null);
    }

    public void deleteVideo(String name) {
        JOptionPane.showMessageDialog(null, "Deleted the video: " + name,
                "deleteVideo", JOptionPane.INFORMATION_MESSAGE);
    }


}
