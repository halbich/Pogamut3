package cz.cuni.amis.pogamut.ut2004.communication.translator.server.state;

import cz.cuni.amis.fsm.FSMState;
import cz.cuni.amis.fsm.FSMTransition;
import cz.cuni.amis.fsm.IFSMState;
import cz.cuni.amis.pogamut.base.communication.messages.InfoMessage;
import cz.cuni.amis.pogamut.ut2004.communication.messages.gbinfomessages.GameInfoMessage;
import cz.cuni.amis.pogamut.ut2004.communication.translator.TranslatorContext;
import cz.cuni.amis.pogamut.ut2004.communication.translator.TranslatorMessages;
import cz.cuni.amis.pogamut.ut2004.communication.translator.UnexpectedMessageException;
import cz.cuni.amis.pogamut.ut2004.communication.translator.server.support.AbstractServerFSMState;
import cz.cuni.amis.pogamut.ut2004.communication.translator.shared.transition.GameInfoTransition;

/**
 * State that expects GameInfo message to came - when it receives GameInfo it switches to the HandshakeControllerState.
 * @author Jimmy
 */
@FSMState(
		map={
				@FSMTransition(
					state=ServerRunningState.class, 
					symbol={GameInfoMessage.class}, 
					transition={GameInfoTransition.class}
				)
			}
)
public class GameInfoExpectedState extends AbstractServerFSMState<InfoMessage, TranslatorContext> {

	@Override
	public void init(TranslatorContext context) {
	}

	@Override
	public void restart(TranslatorContext context) {
	}

	@Override
	public void stateEntering(TranslatorContext context,
			IFSMState<InfoMessage, TranslatorContext> fromState,
			InfoMessage symbol) {
	}

	@Override
	public void stateLeaving(TranslatorContext context,
			IFSMState<InfoMessage, TranslatorContext> toState, InfoMessage symbol) {
	}

	@Override
	protected void innerStateSymbol(TranslatorContext context, InfoMessage symbol) {
		throw new UnexpectedMessageException(TranslatorMessages.unexpectedMessage(this, symbol), context.getLogger(), this);
	}

}