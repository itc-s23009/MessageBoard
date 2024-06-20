package jp.ac.it_college.std.s23009.messageboard.aplication.service


import jp.ac.it_college.std.s23009.messageboard.domain.model.Messages
import jp.ac.it_college.std.s23009.messageboard.domain.repository.MessageRepository

class MessageService(
    private val messageRepository: MessageRepository
){
    /**
     * 新しいメッセージを作成します。
     * @param message 新しいメッセージの情報
     * @return 作成されたメッセージ情報（Messagesオブジェクト）
     */
    fun createMessage(message: Messages): Messages {
        return messageRepository.createMessage(message)
    }
}