package jp.ac.it_college.std.s23009.messageboard.aplication.service

import jp.ac.it_college.std.s23009.messageboard.domain.model.Threads
import jp.ac.it_college.std.s23009.messageboard.domain.repository.ThreadsRepository
import org.springframework.stereotype.Service

@Service
class ThreadService(
    private val threadsRepository: ThreadsRepository
) {
    /**
     * 新しいスレッドを作成します。
     * @param thread 新しいスレッドの情報
     * @return 作成されたスレッド情報（Threadsオブジェクト）
     */
    fun createThread(thread: Threads): Threads {
        return threadsRepository.createThread(thread)
    }
}