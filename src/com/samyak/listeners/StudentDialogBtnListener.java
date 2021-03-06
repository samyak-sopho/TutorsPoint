package com.samyak.listeners;

import com.samyak.Home;
import com.samyak.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// same listener used to display dialogs for students distinguished on the basis of dialog type
public class StudentDialogBtnListener implements ActionListener {

    private JDialog dialog;

    public StudentDialogBtnListener(JDialog dialog) {
        this.dialog = dialog;
        dialog.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Home.getHome().getUserId() == -1 || Home.getHome().getUserName().equals("") || !Home.getHome().getUserType().equals("student")) {
            new ErrorMsgDisplay("Not Signed in. Sign in or Sign up as a Student.", (Component) e.getSource());
            return;
        }

        if (dialog == null || !dialog.isDisplayable()) {
            if (dialog instanceof ManageSubscriptionsDialog)
                dialog = new ManageSubscriptionsDialog();
            else if (dialog instanceof WatchListDialog)
                dialog = new WatchListDialog();
            else if (dialog instanceof InProgressCourseDialog)
                dialog = new InProgressCourseDialog();
            else if (dialog instanceof RateCourseDialog)
                dialog = new RateCourseDialog(Integer.parseInt(((JButton) e.getSource()).getName()));
            dialog.pack();
            dialog.setLocationRelativeTo((Component) e.getSource());
            dialog.setVisible(true);
        } else
            dialog.setVisible(true);
    }
}