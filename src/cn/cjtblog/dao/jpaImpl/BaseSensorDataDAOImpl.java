package cn.cjtblog.dao.jpaImpl;

import java.util.Date;
import java.util.List;

public class BaseSensorDataDAOImpl<T> extends BaseEntityDAOImpl<T> {

	public List<T> getByTimeRange(long nodeId, Date begin, Date end) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE t.sensor.node.id=:nodeId AND t.sendTime between :begin AND :end ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr).setParameter("nodeId",nodeId)
				.setParameter("begin", begin).setParameter("end", end)
				.getResultList();
		return (List<T>) result;
	}
	
	public List<T> getByTimeRange(long nodeId, long sensorId, Date begin, Date end) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE t.sensor.node.id=:nodeId AND t.sensor.node.id=:sensorId AND t.sendTime between :begin and :end ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr).setParameter("sensorId",sensorId).setParameter("nodeId", nodeId)
				.setParameter("begin", begin).setParameter("end", end)
				.getResultList();
		return (List<T>) result;
	}

	public List<T> getAll(long nodeId,long sensorId) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE t.sensor.id=:sensorId AND t.sensor.node.id=:nodeId ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr).setParameter("sensorId",sensorId)
				.setParameter("nodeId", nodeId)
				.getResultList();
		return (List<T>) result;
	}
	
	public List<T> getAll(long nodeId) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE  t.sensor.node.id=:nodeId ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr)
				.setParameter("nodeId", nodeId)
				.getResultList();
		return (List<T>) result;
	}
	
	public T getNewest(long nodeId, long sensorId) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE t.sensor.id=:sensorId AND t.sensor.node.id=:nodeId ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr)
				.setParameter("nodeId", nodeId)
				.setParameter("sensorId", sensorId).setMaxResults(1)
				.getResultList();
		if (result.size() > 0) {
			return (T) result.get(0);
		} else {
			return null;
		}
	}
	public T getNewest(long nodeId) {
		String queryStr = "FROM "
				+ entityClass.getSimpleName()
				+ " t WHERE t.sensor.node.id=:nodeId ORDER BY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr)
				.setParameter("nodeId", nodeId).setMaxResults(1)
				.getResultList();
		if (result.size() > 0) {
			return (T) result.get(0);
		} else {
			return null;
		}
	}

	
	public T getNewest() {
		String queryStr = "FROM " + entityClass.getSimpleName()
				+ " t ORDERBY t.sendTime DESC";
		List result = entityManager.createQuery(queryStr).setMaxResults(1)
				.getResultList();
		if (result.size() > 0) {
			return (T) result.get(0);
		} else {
			return null;
		}
	}
}
