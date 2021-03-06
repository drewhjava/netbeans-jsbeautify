/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drewhamlett.jsbeautify.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration( location = "Advanced",
displayName = "#AdvancedOption_DisplayName_JSBeautify",
keywords = "#AdvancedOption_Keywords_JSBeautify",
keywordsCategory = "Advanced/JSBeautify" )
@org.openide.util.NbBundle.Messages( { "AdvancedOption_DisplayName_JSBeautify=JSBeautify", "AdvancedOption_Keywords_JSBeautify=jsbeautify, javascript, beautify" } )
public final class JSBeautifyOptionsPanelController extends OptionsPanelController {

	private JSBeautifyPanel panel;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport( this );
	private boolean changed;

	@Override
	public void update() {
		getPanel().load();
		changed = false;
	}

	@Override
	public void applyChanges() {
		getPanel().store();
		changed = false;
	}

	@Override
	public void cancel() {
		// need not do anything special, if no changes have been persisted yet
	}

	@Override
	public boolean isValid() {
		return getPanel().valid();
	}

	@Override
	public boolean isChanged() {
		return changed;
	}

	@Override
	public HelpCtx getHelpCtx() {
		return null;
	}

	@Override
	public JComponent getComponent( Lookup masterLookup ) {
		return getPanel();
	}

	@Override
	public void addPropertyChangeListener( PropertyChangeListener l ) {
		pcs.addPropertyChangeListener( l );
	}

	@Override
	public void removePropertyChangeListener( PropertyChangeListener l ) {
		pcs.removePropertyChangeListener( l );
	}

	private JSBeautifyPanel getPanel() {
		if ( panel == null ) {
			panel = new JSBeautifyPanel( this );
		}
		return panel;
	}

	void changed() {
		if ( !changed ) {
			changed = true;
			pcs.firePropertyChange( OptionsPanelController.PROP_CHANGED, false, true );
		}
		pcs.firePropertyChange( OptionsPanelController.PROP_VALID, null, null );
	}
}
