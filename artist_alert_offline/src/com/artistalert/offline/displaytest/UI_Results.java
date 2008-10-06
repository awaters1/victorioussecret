package com.artistalert.offline.displaytest;
import java.awt.Component;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.*;

import org.farng.mp3.MP3File;



/**
 * 
 * @author Charlie
 * 
 * modified to test sending data between frames
 * @author Woojoon
 * 
 */
public class UI_Results extends javax.swing.JFrame {

	/** Creates new form results_uc */
//	public UI_Results() {
//		initComponents();
//	}
	
	/** TEST constructor w/ reader */
	public UI_Results(Map<String, Collection<String>> data) {
		initComponents(data);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents(Map<String, Collection<String>> artists) {

		paneArtists = new javax.swing.JScrollPane();
		listArtists = new javax.swing.JList();
		paneAlbums = new javax.swing.JScrollPane();
		listAlbums = new javax.swing.JList();
		labelArtists = new javax.swing.JLabel();
		labelAlbums = new javax.swing.JLabel();
		buttonExport = new javax.swing.JButton();
		buttonExit = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		/**
		 * list model defines entries of JList
		 */
		DefaultListModel listModelA = new DefaultListModel();
		DefaultListModel listModelB = new DefaultListModel();
		/**
		 * Iterator will traverse the entries in the map "Artist"
		 * entries will be added to the list models
		 * 
		 */
		Set entries = artists.entrySet();
		Iterator iter = entries.iterator();
        while (iter.hasNext()) {
        	Map.Entry curr = (Entry) iter.next();
        	String key = (String) curr.getKey();
			Collection<String> value = (Collection<String>) curr.getValue();
			for (int i=0; i<value.size(); i++) {
				listModelB.addElement(value);
			}
			listModelA.addElement(key);
		}
        
        listArtists = new JList(listModelA);
//		listArtists.setModel(new javax.swing.AbstractListModel()
//		{
//			/**
//			 * from reader.java
//			 */
//	
//
//			String[] strings = { "hello 1", "Item 2", "Item 3", "Item 4",
//					"Item 5" };
//
//			public int getSize() {
//				return strings.length;
//			}
//
//			public Object getElementAt(int i) {
//				return strings[i];
//			}
//		});
		listArtists
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		paneArtists.setViewportView(listArtists);

		listAlbums = new JList(listModelB);
//		listAlbums.setModel(new javax.swing.AbstractListModel()
//		{
//			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
//					"Item 5" };
//
//			public int getSize() {
//				return strings.length;
//			}
//
//			public Object getElementAt(int i) {
//				return strings[i];
//			}
//		});
		listAlbums
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		paneAlbums.setViewportView(listAlbums);
        
		labelArtists.setText("Artists (X)");

		labelAlbums.setText("Albums (Y)");

		buttonExport.setText("Export");

		buttonExit.setText("Exit");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(24, 24, 24)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				paneArtists,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				117,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				labelArtists))
														.addComponent(
																buttonExport))
										.addGap(57, 57, 57)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																labelAlbums)
														.addComponent(
																paneAlbums,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																125,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																buttonExit))
										.addContainerGap(42, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																labelArtists)
														.addComponent(
																labelAlbums))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																paneAlbums,
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																paneArtists,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																141,
																Short.MAX_VALUE))
										.addGap(31, 31, 31)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																buttonExit)
														.addComponent(
																buttonExport))
										.addContainerGap(41, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new UI_Results(reader).setVisible(true);
//			}
//		});
//	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonExit;
	private javax.swing.JButton buttonExport;
	private javax.swing.JLabel labelAlbums;
	private javax.swing.JLabel labelArtists;
	private javax.swing.JList listAlbums;
	private javax.swing.JList listArtists;
	private javax.swing.JScrollPane paneAlbums;
	private javax.swing.JScrollPane paneArtists;
	// End of variables declaration//GEN-END:variables

}
