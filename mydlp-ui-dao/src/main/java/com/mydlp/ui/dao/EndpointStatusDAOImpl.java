package com.mydlp.ui.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mydlp.ui.domain.EndpointStatus;

@Repository("endpointStatusDAO")
@Transactional
public class EndpointStatusDAOImpl extends AbstractLogDAO implements EndpointStatusDAO {

	@Override
	public void upToDateEndpoint(String ipAddress) {
		upToDateEndpoint(ipAddress, null);
	}
	
	@Override
	public void upToDateEndpoint(String ipAddress, String username) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.add(Restrictions.eq("ipAddress", ipAddress));
		@SuppressWarnings("unchecked")
		List<EndpointStatus> list = getHibernateTemplate().findByCriteria(criteria);
		EndpointStatus endpointStatus = DAOUtil.getSingleResult(list);
		if (endpointStatus == null)
		{
			endpointStatus = new EndpointStatus();
			endpointStatus.setIpAddress(ipAddress);
			endpointStatus.setFirstAppeared(new Date());
			endpointStatus.setUsername(username);
		}
		endpointStatus.setIsUpToDate(true);
		endpointStatus.setLastUpdate(new Date());
		endpointStatus.setUsername(username);
		
		getHibernateTemplate().saveOrUpdate(endpointStatus);
	}

	@Override
	public void outOfDateAllEndpoints() {
		getHibernateTemplate().bulkUpdate("update EndpointStatus es set es.isUpToDate=false");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EndpointStatus> getEndpointStatuses(String searchString, Integer offset,
			Integer limit) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.addOrder(Order.desc("lastUpdate"));
		criteria = applySearchCriteria(criteria, searchString);
		return criteria.getExecutableCriteria(getSession())
			.setFirstResult(offset)
			.setMaxResults(limit)
			.list();
	}

	@Override
	public Long getEndpointStatusCount(String searchString) {
		DetachedCriteria criteria = 
				DetachedCriteria.forClass(EndpointStatus.class)
					.setProjection(Projections.rowCount());
		criteria = applySearchCriteria(criteria, searchString);
		@SuppressWarnings("unchecked")
		List<Long> returnList = getHibernateTemplate().findByCriteria(criteria);
		return DAOUtil.getSingleResult(returnList);
	}
	
	protected DetachedCriteria applySearchCriteria(DetachedCriteria detachedCriteria, String searchStr)
	{
		DetachedCriteria criteria = detachedCriteria;
		
		if (searchStr == null || searchStr.length() == 0)
			return criteria;
		
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.sqlRestriction("(1=0)")); //  defaults to false
		
		disjunction.add(Restrictions.ilike("ipAddress", "%" + searchStr + "%"));
		disjunction.add(Restrictions.ilike("username", "%" + searchStr + "%"));
		
		criteria = criteria.add(disjunction);
		
		return criteria;
	}

			
}
