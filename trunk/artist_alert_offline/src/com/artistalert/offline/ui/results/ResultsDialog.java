package com.artistalert.offline.ui.results;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.artistalert.offline.tags.Reader;

/**
 * 
 * @author Charlie
 * 
 *         modified to test sending data between frames
 * @author Woojoon
 * 
 */
public class ResultsDialog extends javax.swing.JFrame {

	/** Creates new form results_uc */
	// public UI_Results() {
	// initComponents();
	// }
	/** TEST constructor w/ reader */
	public ResultsDialog(Map<String, Collection<String>> data) {
		initComponents(data);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents(final Map<String, Collection<String>> artists) {

		if(artists.isEmpty())
		{
			System.out.println("Directory Empty");
			//System.exit(0);
			Thread.currentThread().stop();
		}
		
		paneArtists = new javax.swing.JScrollPane();
		listArtists = new javax.swing.JList();
		paneAlbums = new javax.swing.JScrollPane();
		listAlbums = new javax.swing.JList();
		labelArtists = new javax.swing.JLabel();
		labelAlbums = new javax.swing.JLabel();
		buttonExport = new javax.swing.JButton();
		buttonExit = new javax.swing.JButton();

		setTitle("ArtistAlert - Scanner");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		/**
		 * list model defines entries of JList
		 */
		DefaultListModel listModelA = new DefaultListModel();
		final DefaultListModel listModelB = new DefaultListModel();
		/**
		 * Iterator will traverse the entries in the map "Artist" entries will
		 * be added to the list models
		 * 
		 */
		
		Set entries = artists.entrySet();
		Iterator iter = entries.iterator();
		while (iter.hasNext()) {
			Map.Entry curr = (Entry) iter.next();
			String key = (String) curr.getKey();
			listModelA.addElement(key);
		}
		
		listArtists = new JList(listModelA);
		listArtists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listArtists.setSelectedIndex(0);
		
		
		class ListArtistSelectionHandeler implements ListSelectionListener {
		    public void valueChanged(ListSelectionEvent e) {
		    	artistSelected = listArtists.getSelectedValue().toString();
		    	Collection<String> value = artists.get(artistSelected);
		    	Iterator albumIterator = value.iterator();
		    	listModelB.clear();
				while(albumIterator.hasNext())
				{
					listModelB.addElement(albumIterator.next());
				}
		    }
		}
		listArtists.addListSelectionListener(new ListArtistSelectionHandeler());

		
		artistSelected = listArtists.getSelectedValue().toString();
		System.out.println(artistSelected);
		Collection<String> value = artists.get(artistSelected);
		System.out.println(value.toString());
		Iterator albumIterator = value.iterator();
		while(albumIterator.hasNext())
		{
			listModelB.addElement(albumIterator.next());
		}
		/*
		for (int i = 0; i < value.length; i++) {
			listModelB.addElement(value[i]);
		}*/
		listAlbums = new JList(listModelB);
		
		paneArtists.setViewportView(listArtists);

		

		
		// listAlbums.setModel(new javax.swing.AbstractListModel()
		// {
		// String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
		// "Item 5" };
		//
		// public int getSize() {
		// return strings.length;
		// }
		//
		// public Object getElementAt(int i) {
		// return strings[i];
		// }
		// });
		listAlbums.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		paneAlbums.setViewportView(listAlbums);

		labelArtists.setText("Artists");

		labelAlbums.setText("Albums");

		buttonExport.setText("Export");
		buttonExport.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					buttonExportActionPerformed(evt, artists);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		buttonExit.setText("Exit");
		buttonExit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonExitActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout
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
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				labelArtists))
														.addComponent(buttonExport))
										.addGap(57, 57, 57)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(labelAlbums)
														.addComponent(
																paneAlbums,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE,
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
														.addComponent(labelArtists)
														.addComponent(labelAlbums))
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
														.addComponent(buttonExit)
														.addComponent(buttonExport))
										.addContainerGap(41, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 * @throws FileNotFoundException
	 */
	// public static void main(String args[]) {
	// java.awt.EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// new UI_Results(reader).setVisible(true);
	// }
	// });
	// }
	@SuppressWarnings("static-access")
	private void buttonExportActionPerformed(java.awt.event.ActionEvent evt,
			Map<String, Collection<String>> artists)
			throws FileNotFoundException {// GEN-FIRST:event_buttonExportActionPerformed

		JFileChooser saver = new JFileChooser();
		saver.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		// saver.setSelectedFile(new File("export.xml"));
		saver.showSaveDialog(getParent());

		// while(saver.getSelectedFile() == saver.CANCEL_OPTION)
		// saver.showSaveDialog(getParent());
		File file = saver.getSelectedFile();

		// System.out.println(file.getName());
		// File file = new File("export.xml");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (file.canWrite()) {
			java.io.PrintWriter output = new java.io.PrintWriter(file);
			output.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			output.println("<artists>");
			final Iterator<String> artistItr = artists.keySet().iterator();
			artistItr.hasNext();
			while (artistItr.hasNext()) {
				final String artist = artistItr.next();				
				output.println("\t<artist>");
				output.println("\t\t" + artist);
				output.println("\t\t<albums>");
				final Iterator<String> albumItr = artists.get(artist)
						.iterator();
				while (albumItr.hasNext()) {
					final String album = albumItr.next();
					output.println("\t\t\t<album>");
					output.println("\t\t\t\t" + album);
					output.println("\t\t\t</album>");
				}
				output.println("\t\t</albums>");
				output.println("\t</artist>");
			}

			output.print("</artists>");
			output.close();
		}
		System.exit(0);

	}// GEN-LAST:event_buttonChooseActionPerformed

	private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonExitActionPerformed

		System.exit(0);

	}// GEN-LAST:event_buttonExitActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonExit;
	private javax.swing.JButton buttonExport;
	private javax.swing.JLabel labelAlbums;
	private javax.swing.JLabel labelArtists;
	private javax.swing.JList listAlbums;
	private javax.swing.JList listArtists;
	private javax.swing.JScrollPane paneAlbums;
	private javax.swing.JScrollPane paneArtists;
	private String artistSelected;
	// End of variables declaration//GEN-END:variables

}