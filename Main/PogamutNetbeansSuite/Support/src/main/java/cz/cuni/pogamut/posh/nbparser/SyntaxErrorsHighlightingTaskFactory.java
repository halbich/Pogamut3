/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cuni.pogamut.posh.nbparser;

import java.util.Collection;
import java.util.Collections;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.SchedulerTask;
import org.netbeans.modules.parsing.spi.TaskFactory;

/**
 *
 * @author Honza
 */
public class SyntaxErrorsHighlightingTaskFactory extends TaskFactory {

    @Override
    public Collection<? extends SchedulerTask> create (Snapshot snapshot) {
        return Collections.singleton (new SyntaxErrorsHighlightingTask ());
    }
}
