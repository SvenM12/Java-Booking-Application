/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.fileio;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import static com.mycompany.fileio.FileIO.readTextFile;
import java.awt.Color;
import java.awt.image.ColorModel;
import java.text.Format;
import java.text.SimpleDateFormat;
/**
 *
 * @author Sven
 */
public class EscapeRooms extends javax.swing.JFrame {
    
    ReviewBooking showingText = new ReviewBooking();
    LocalDate year = LocalDate.now();
    ArrayList <Integer> roomNumbers = new ArrayList<>();
    ArrayList <String> names = new ArrayList<>();
    ArrayList <String> surnames = new ArrayList<>();
    ArrayList <String> contactNumber = new ArrayList<>();
    ArrayList <LocalDate> dates = new ArrayList<>();
    ArrayList <Integer> numberOfPersons = new ArrayList<>();
    ArrayList <String> extraRequirements = new ArrayList<>();
    ArrayList <Integer> filteredBookings = new ArrayList<>();
    /*String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
        "22", "23", "24", "25", "26", "27", "28", "29", "30"};*/
    String[] days = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
        "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    /*String[] febDays = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
        "22", "23", "24", "25", "26", "27", "28", "29"};*/
    /**
     * Creates new form EscapeRooms
     */
    public EscapeRooms() {
        
        initComponents();
        LoadBookings();
        AvailableIcons();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("YYYY");
        LocalDate year1 = year.plusYears(1);
        String formattedNextYear = year1.format(format);
        LocalDate nextNextYear = year1.plusYears(1);
        String formattedNextNextYear = nextNextYear.format(format);
        YearSelect.addItem(formattedNextYear);
        YearSelect.addItem(formattedNextNextYear);
        
    }
    public EscapeRooms(JComboBox cCombo)
    {
        this.DaySelect = cCombo;
        this.MonthSelect = cCombo;
        this.YearSelect = cCombo;
    }
    
    /*
    This method grabs the contents of the assigned file and stores the specificed
    values in ArrayLists for future use.
    */
    public final void LoadBookings() {
        roomNumbers.clear();
        names.clear();
        surnames.clear();
        contactNumber.clear();
        dates.clear();
        numberOfPersons.clear();
        extraRequirements.clear();
        String bookingDetails = readTextFile("bookings.txt");
        String[] bookings = bookingDetails.split("\n");
        int currentIndex = 0;
        for (String element : bookings)
        {
            String[] allData = element.split(",");
            roomNumbers.add(Integer.parseInt(allData[0]));
            names.add(allData[1]);
            surnames.add(allData[2]);
            contactNumber.add(allData[3]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate localDate = LocalDate.parse(allData[4], formatter);
            dates.add(localDate);
            
            
            String noOfPeople = allData[5];
            String test = noOfPeople.substring(0,1);
            numberOfPersons.add(Integer.parseInt(test));
            try
            {
                extraRequirements.add(allData[6]);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                extraRequirements.add("");
            }
        }
}
    
    public void FilterBookings() {
        /*
        This filters the bookings and stores the index of the currently selected
        boooked date into another arraylist.
        */
        String month = EscapeRooms.MonthSelect.getSelectedItem().toString();
        String day = EscapeRooms.DaySelect.getSelectedItem().toString();
        String year = EscapeRooms.YearSelect.getSelectedItem().toString();
         switch(month)
        {
            case "January" -> month = "01";
            case "February" -> month = "02";
            case "March" -> month = "03";
            case "April" -> month = "04";
            case "May" -> month = "05";
            case "June" -> month = "06";
            case "July" -> month = "07";
            case "August" -> month = "08";
            case "September" -> month = "09";
            case "October" -> month = "10";
            case "November" -> month = "11";
            case "December" -> month = "12";
        }
            String testing = day + "/" + month + "/" + year;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate selectedDate = LocalDate.parse(testing, formatter);
             boolean doesContain = dates.contains(selectedDate);
             filteredBookings.clear();
            for (int i = 0; i < dates.size(); i++)
            {
                if (dates.get(i).equals(selectedDate))
                {
                    filteredBookings.add(i);
                }
            }
    }
    /*
    The method ShowReview() allows for the ReviewBooking jframe to be displayed if a booking
    has been already booked.
    */
    public void ShowReview()
    {
        switch (ReviewBooking.roomNo) {
            case 1 -> {
                showingText.Name.setText(names.get(filteredBookings.get(0)));
                showingText.Surname.setText(surnames.get(filteredBookings.get(0)));
                showingText.contactNo.setText(contactNumber.get(filteredBookings.get(0)));
                int noOfPeople = numberOfPersons.get(filteredBookings.get(0));
                String test = String.valueOf(noOfPeople);
                showingText.noOfPeople.setText(test);
                showingText.ExtraReq.setText(extraRequirements.get(filteredBookings.get(0)));
            }
            case 2 -> {
                showingText.Name.setText(names.get(filteredBookings.get(1)));
                showingText.Surname.setText(surnames.get(filteredBookings.get(1)));
                showingText.contactNo.setText(contactNumber.get(filteredBookings.get(1)));
                int noOfPeople = numberOfPersons.get(filteredBookings.get(1));
                String test = String.valueOf(noOfPeople);
                showingText.noOfPeople.setText(test);
                showingText.ExtraReq.setText(extraRequirements.get(filteredBookings.get(1)));
            }
            case 3 -> {
                showingText.Name.setText(names.get(filteredBookings.get(2)));
                showingText.Surname.setText(surnames.get(filteredBookings.get(2)));
                showingText.contactNo.setText(contactNumber.get(filteredBookings.get(2)));
                int noOfPeople = numberOfPersons.get(filteredBookings.get(2));
                String test = String.valueOf(noOfPeople);
                showingText.noOfPeople.setText(test);
                showingText.ExtraReq.setText(extraRequirements.get(filteredBookings.get(2)));
            }
            case 4 -> {
                showingText.Name.setText(names.get(filteredBookings.get(3)));
                showingText.Surname.setText(surnames.get(filteredBookings.get(3)));
                showingText.contactNo.setText(contactNumber.get(filteredBookings.get(3)));
                int noOfPeople = numberOfPersons.get(filteredBookings.get(3));
                String test = String.valueOf(noOfPeople);
                showingText.noOfPeople.setText(test);
                showingText.ExtraReq.setText(extraRequirements.get(filteredBookings.get(3)));
            }
            case 5 -> {
                showingText.Name.setText(names.get(filteredBookings.get(4)));
                showingText.Surname.setText(surnames.get(filteredBookings.get(4)));
                showingText.contactNo.setText(contactNumber.get(filteredBookings.get(4)));
                int noOfPeople = numberOfPersons.get(filteredBookings.get(4));
                String test = String.valueOf(noOfPeople);
                showingText.noOfPeople.setText(test);
                showingText.ExtraReq.setText(extraRequirements.get(filteredBookings.get(4)));
            }
            default -> {
            }
        }
    }
    /*
    This method loads the default icons for when the program is running and no date
    is selected or there are no bookings on a specific dates.
    */
    public final void AvailableIcons()
    {
        jLabel3.setText("All rooms are available");

        ImageIcon available1 = new ImageIcon("home assignment images-20221217\\room1_available.png");
        Image availableImage1 = available1.getImage();
        Image resizedAvailableImg1 = availableImage1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        available1 = new ImageIcon(resizedAvailableImg1);
        jButton2.setIcon(available1);
        jButton2.setBackground(Color.white);

        ImageIcon available2 = new ImageIcon("home assignment images-20221217\\room2_available.png");
        Image availableImage2 = available2.getImage();
        Image resizedAvailableImg2 = availableImage2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        available2 = new ImageIcon(resizedAvailableImg2);
        jButton3.setIcon(available2);
        jButton3.setBackground(Color.white);

        ImageIcon available3 = new ImageIcon("home assignment images-20221217\\room3_available.png");
        Image availableImage3 = available3.getImage();
        Image resziedAvailableImg3 = availableImage3.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        available3 = new ImageIcon(resziedAvailableImg3);
        jButton4.setIcon(available3);
        jButton4.setBackground(Color.white);

        ImageIcon available4 = new ImageIcon("home assignment images-20221217\\room4_available.png");
        Image availableImage4 = available4.getImage();
        Image resizedAvailableImg4 = availableImage4.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        available4 = new ImageIcon(resizedAvailableImg4);
        jButton5.setIcon(available4);
        jButton5.setBackground(Color.white);

        ImageIcon available5 = new ImageIcon("home assignment images-20221217\\room5_available.png");
        Image availableImage5 = available5.getImage();
        Image resizedAvailableImg5 = availableImage5.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        available5 = new ImageIcon(resizedAvailableImg5);
        jButton6.setIcon(available5);
        jButton6.setBackground(Color.white);
    }
    /*
    This updates the icons accordingly when a bookings exists on a specific date.
    */
    public void UpdateIcons()
    {
        AvailableIcons();
             for (int i : filteredBookings)
             {
                 int roomsBooked = 0;
                 for (LocalDate s : dates)
                 {
                      if (dates.get(i).isEqual(s))
                {
                    
                    roomsBooked++;
                    
                    boolean isBooked = filteredBookings.contains(i);
                    LocalDate currentDate = dates.get(i);
                    boolean containsCurrentDate = dates.contains(currentDate);
                    
                    if (isBooked && containsCurrentDate)
                    {
                        if (roomsBooked == 1)
                            {
                                jLabel3.setText("4 rooms are available");
                                ImageIcon booked1 = new ImageIcon("home assignment images-20221217\\room1_booked.png");
                                Image bookedImage1 = booked1.getImage();
                                Image resizedBookedImg1 = bookedImage1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                booked1 = new ImageIcon(resizedBookedImg1);
                                jButton2.setIcon(booked1);
                                jButton2.setBackground(Color.red);
                                
                            }
                            if (roomsBooked == 2)
                            {
                                jLabel3.setText("3 rooms are available");
                                ImageIcon booked2 = new ImageIcon("home assignment images-20221217\\room2_booked.png");
                                Image bookedImage2 = booked2.getImage();
                                Image resizedBookedImg2 = bookedImage2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                booked2 = new ImageIcon(resizedBookedImg2);
                                jButton3.setIcon(booked2);
                                jButton3.setBackground(Color.red);
                                
                            }
                            if (roomsBooked == 3)
                            {
                                jLabel3.setText("2 rooms are available");
                                ImageIcon booked3 = new ImageIcon("home assignment images-20221217\\room3_booked.png");
                                Image bookedImage3 = booked3.getImage();
                                Image resizedBookedImg3 = bookedImage3.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                booked3 = new ImageIcon(resizedBookedImg3);
                                jButton4.setIcon(booked3);
                                jButton4.setBackground(Color.red);
                            }
                            if (roomsBooked == 4)
                            {
                                jLabel3.setText("1 rooms is available");
                                ImageIcon booked4 = new ImageIcon("home assignment images-20221217\\room4_booked.png");
                                Image bookedImage4 = booked4.getImage();
                                Image resizedBookedImg4 = bookedImage4.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                booked4 = new ImageIcon(resizedBookedImg4);
                                jButton5.setIcon(booked4);
                                jButton5.setBackground(Color.red);
                            }
                            if (roomsBooked == 5)
                            {
                                jLabel3.setText("no rooms are available");
                                ImageIcon booked5 = new ImageIcon("home assignment images-20221217\\room5_booked.png");
                                Image bookedImage5 = booked5.getImage();
                                Image resizedBookedImg5 = bookedImage5.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                booked5 = new ImageIcon(resizedBookedImg5);
                                jButton6.setIcon(booked5);
                                jButton6.setBackground(Color.red);
                            }
                    }   
                    
                }
                 }
             }
        if (filteredBookings.isEmpty())
        {
                AvailableIcons();
        }
    }
     
    /*
    This method updates the days combo box depending on the month selected.
    */
    public void UpdateMonths() {
       
        if (MonthSelect.getSelectedIndex() == 0 || MonthSelect.getSelectedIndex() == 11
                || MonthSelect.getSelectedIndex() == 2  || MonthSelect.getSelectedIndex() == 4
               || MonthSelect.getSelectedIndex() == 6 || MonthSelect.getSelectedIndex() == 7
                || MonthSelect.getSelectedIndex() == 9) {
            DaySelect.removeAllItems();
            for (int i = 01; i < 32; i++)
                {
                    String s = Integer.toString(i);
                    DaySelect.addItem(s);
                }
        }
        
        if (MonthSelect.getSelectedIndex() == 1) {
            DaySelect.removeAllItems();
            if (YearSelect.getSelectedIndex() == 0)
            {
                if (year.plusYears(1).isLeapYear()) {
                for (int i = 01; i < 30; i++)
                {
                    String s = Integer.toString(i);
                    DaySelect.addItem(s);
                }
                }
            }
            if (YearSelect.getSelectedIndex() == 1)
            {
                if (year.plusYears(1).isLeapYear()) {
                for (int i = 01; i < 29; i++)
                {
                    String s = Integer.toString(i);
                    DaySelect.addItem(s);
                }
                }
            }
        }
        if (MonthSelect.getSelectedIndex() == 3 || MonthSelect.getSelectedIndex() == 5
                 || MonthSelect.getSelectedIndex() == 8) {
            DaySelect.removeAllItems();
            for (int i = 01; i < 31; i++)
                {
                    String s = Integer.toString(i);
                    DaySelect.addItem(s);
                }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        MonthSelect = new javax.swing.JComboBox<>();
        YearSelect = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        DaySelect = new javax.swing.JComboBox<>();
        GetBookings = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Escape Rooms");

        jLabel1.setText("Month and Year:");

        MonthSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        MonthSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonthSelectActionPerformed(evt);
            }
        });

        YearSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YearSelectActionPerformed(evt);
            }
        });
        YearSelect.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                YearSelectPropertyChange(evt);
            }
        });

        jLabel2.setText("Day:");

        DaySelect.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                DaySelectVetoableChange(evt);
            }
        });

        GetBookings.setText("Get Bookings");
        GetBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetBookingsActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setText("All rooms are available");

        jMenu3.setText("File");

        jMenuItem2.setText("Refresh Bookings");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Help");

        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(DaySelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(MonthSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(GetBookings)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(YearSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(MonthSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(DaySelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GetBookings))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MonthSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonthSelectActionPerformed
        // TODO add your handling code here:
        //DaySelect.setValue("tesst");
        UpdateMonths();
    }//GEN-LAST:event_MonthSelectActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        LoadBookings();
        FilterBookings();
        UpdateIcons();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int exit = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Please confirm", JOptionPane.YES_NO_OPTION);
        
        if (exit == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        // opens the about jframe
        new About().setVisible(true);
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void GetBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GetBookingsActionPerformed
        // TODO add your handling code here:
        FilterBookings();
        UpdateIcons();
    }//GEN-LAST:event_GetBookingsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        MakeABooking.roomNo = 1;
        ReviewBooking.roomNo = 1;
        String upperText = jLabel3.getText();
        if (upperText.equals("All rooms are available"))
        {
            new MakeABooking().setVisible(true);
           
        }
        else
        {
            showingText.setVisible(true);
            ShowReview();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void DaySelectVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_DaySelectVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_DaySelectVetoableChange

    private void YearSelectPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_YearSelectPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_YearSelectPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        MakeABooking.roomNo = 2;
        ReviewBooking.roomNo = 2;
        String upperText = jLabel3.getText();
        if (filteredBookings.size() < 2)
        {
            new MakeABooking().setVisible(true);
            
        }
        else
        {
            showingText.setVisible(true);
            ShowReview();
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MakeABooking.roomNo = 3;
        ReviewBooking.roomNo = 3;
        String upperText = jLabel3.getText();
        if (filteredBookings.size() < 3)
        {
            new MakeABooking().setVisible(true);
            
        }
        else
        {
            showingText.setVisible(true);
            ShowReview();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        MakeABooking.roomNo = 4;
        ReviewBooking.roomNo = 4;
        String upperText = jLabel3.getText();
        if (filteredBookings.size() < 4)
        {
            new MakeABooking().setVisible(true);
           
        }
        else
        {
            
            showingText.setVisible(true);
            ShowReview();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        MakeABooking.roomNo = 5;
        ReviewBooking.roomNo = 5;
        String upperText = jLabel3.getText();
        if (filteredBookings.size() < 5)
        {
            new MakeABooking().setVisible(true);
           
        }
        else
        {
            showingText.setVisible(true);
            ShowReview();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void YearSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YearSelectActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_YearSelectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EscapeRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EscapeRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EscapeRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EscapeRooms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EscapeRooms().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> DaySelect;
    private javax.swing.JButton GetBookings;
    public static javax.swing.JComboBox<String> MonthSelect;
    public static javax.swing.JComboBox<String> YearSelect;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    // End of variables declaration//GEN-END:variables
}
