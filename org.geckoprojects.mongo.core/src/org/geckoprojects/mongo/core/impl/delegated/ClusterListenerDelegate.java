package org.geckoprojects.mongo.core.impl.delegated;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mongodb.event.ClusterClosedEvent;
import com.mongodb.event.ClusterDescriptionChangedEvent;
import com.mongodb.event.ClusterListener;
import com.mongodb.event.ClusterOpeningEvent;

public class ClusterListenerDelegate implements ClusterListener {

	ReadWriteLock lock = new ReentrantReadWriteLock();
	List<ClusterListener> delegte = new ArrayList<>();

	public void bindListener(ClusterListener listener) {

		lock.writeLock().lock();
		try {
			delegte.add(listener);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void unbindListener(ClusterListener listener) {
		lock.writeLock().lock();
		try {
			delegte.remove(listener);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void clusterOpening(ClusterOpeningEvent event) {
		lock.readLock().lock();
		try {
			delegte.forEach(l -> l.clusterOpening(event));
		} finally {
			lock.readLock().unlock();
		}
	}

	public void clusterClosed(ClusterClosedEvent event) {
		lock.readLock().lock();
		try {
			delegte.forEach(l -> l.clusterClosed(event));
		} finally {
			lock.readLock().unlock();
		}
	}

	public void clusterDescriptionChanged(ClusterDescriptionChangedEvent event) {
		lock.readLock().lock();
		try {

			delegte.forEach(l -> l.clusterDescriptionChanged(event));
		} finally {
			lock.readLock().unlock();
		}
	}
}
