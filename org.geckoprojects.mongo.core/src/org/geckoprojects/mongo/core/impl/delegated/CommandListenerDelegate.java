package org.geckoprojects.mongo.core.impl.delegated;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;

public class CommandListenerDelegate implements CommandListener {

	ReadWriteLock lock = new ReentrantReadWriteLock();
	List<CommandListener> delegte = new ArrayList<>();
	CommandListener c;

	public void bindListener(CommandListener listener) {

		lock.writeLock().lock();
		try {
			delegte.add(listener);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void unbindListener(CommandListener listener) {
		lock.writeLock().lock();
		try {
			delegte.remove(listener);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void commandStarted(CommandStartedEvent event) {
		lock.readLock().lock();
		try {
			delegte.forEach(l -> l.commandStarted(event));
		} finally {
			lock.readLock().unlock();
		}
	}

	public void commandSucceeded(CommandSucceededEvent event) {
		lock.readLock().lock();
		try {
			delegte.forEach(l -> l.commandSucceeded(event));
		} finally {
			lock.readLock().unlock();
		}
	}

	public void commandFailed(CommandFailedEvent event) {
		lock.readLock().lock();
		try {

			delegte.forEach(l -> l.commandFailed(event));
		} finally {
			lock.readLock().unlock();
		}
	}

}
