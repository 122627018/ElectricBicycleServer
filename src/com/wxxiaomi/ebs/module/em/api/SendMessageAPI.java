package com.wxxiaomi.ebs.module.em.api;

import com.wxxiaomi.ebs.module.em.comm.body.AudioMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.ImgMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.MessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.TextMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.VideoMessageBody;

/**
 * This interface is created for RestAPI of Sending Message, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.hyphenate.io
 */
public interface SendMessageAPI {
	/**
	 * Send message
	 * POST
	 * 
	 * @param payload
	 *            message body
	 * @return
	 * @see MessageBody
	 * @see TextMessageBody
	 * @see ImgMessageBody
	 * @see AudioMessageBody
	 * @see VideoMessageBody
	 * @see CommandMessageBody
	 */
	Object sendMessage(Object payload);
}
