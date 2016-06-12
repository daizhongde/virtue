package person.daizhongde.virtue.hibernate.study;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TAuthorityModule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see person.daizhongde.virtue.hibernate.study.TAuthorityModule
 * @author MyEclipse Persistence Tools
 */
public class TAuthorityModuleJNDIDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TAuthorityModuleJNDIDAO.class);
	// property constants
	public static final String _CMNAME = "CMname";
	public static final String _NMLEVEL = "NMlevel";
	public static final String _CMLEAF = "CMleaf";
	public static final String _NMORDER = "NMorder";
	public static final String _CMTARGET = "CMtarget";
	public static final String _CMICONCLS = "CMiconcls";
	public static final String _CMEXPANDED = "CMexpanded";
	public static final String _CMCHECKED = "CMchecked";
	public static final String _CMPATH = "CMpath";
	public static final String _CMNOTE = "CMnote";
	public static final String _CMCIP = "CMcip";
	public static final String _NMCUSER = "NMcuser";
	public static final String _CMMIP = "CMmip";
	public static final String _NMMUSER = "NMmuser";

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("cpabJNDI");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void save(TAuthorityModule transientInstance) {
		log.debug("saving TAuthorityModule instance");
		try {
			sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TAuthorityModule persistentInstance) {
		log.debug("deleting TAuthorityModule instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TAuthorityModule findById(java.lang.Integer id) {
		log.debug("getting TAuthorityModule instance with id: " + id);
		try {
			TAuthorityModule instance = (TAuthorityModule) sessionFactory
					.getCurrentSession()
					.get("person.daizhongde.virtue.ssh.TAuthorityModule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TAuthorityModule instance) {
		log.debug("finding TAuthorityModule instance by example");
		try {
			List results = sessionFactory
					.getCurrentSession()
					.createCriteria(
							"person.daizhongde.virtue.ssh.TAuthorityModule")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TAuthorityModule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TAuthorityModule as model where model."
					+ propertyName + "= ?";
			Query queryObject = sessionFactory.getCurrentSession().createQuery(
					queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCMname(Object CMname) {
		return findByProperty(_CMNAME, CMname);
	}

	public List findByNMlevel(Object NMlevel) {
		return findByProperty(_NMLEVEL, NMlevel);
	}

	public List findByCMleaf(Object CMleaf) {
		return findByProperty(_CMLEAF, CMleaf);
	}

	public List findByNMorder(Object NMorder) {
		return findByProperty(_NMORDER, NMorder);
	}

	public List findByCMtarget(Object CMtarget) {
		return findByProperty(_CMTARGET, CMtarget);
	}

	public List findByCMiconcls(Object CMiconcls) {
		return findByProperty(_CMICONCLS, CMiconcls);
	}

	public List findByCMexpanded(Object CMexpanded) {
		return findByProperty(_CMEXPANDED, CMexpanded);
	}

	public List findByCMchecked(Object CMchecked) {
		return findByProperty(_CMCHECKED, CMchecked);
	}

	public List findByCMpath(Object CMpath) {
		return findByProperty(_CMPATH, CMpath);
	}

	public List findByCMnote(Object CMnote) {
		return findByProperty(_CMNOTE, CMnote);
	}

	public List findByCMcip(Object CMcip) {
		return findByProperty(_CMCIP, CMcip);
	}

	public List findByNMcuser(Object NMcuser) {
		return findByProperty(_NMCUSER, NMcuser);
	}

	public List findByCMmip(Object CMmip) {
		return findByProperty(_CMMIP, CMmip);
	}

	public List findByNMmuser(Object NMmuser) {
		return findByProperty(_NMMUSER, NMmuser);
	}

	public List findAll() {
		log.debug("finding all TAuthorityModule instances");
		try {
			String queryString = "from TAuthorityModule";
			Query queryObject = sessionFactory.getCurrentSession().createQuery(
					queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TAuthorityModule merge(TAuthorityModule detachedInstance) {
		log.debug("merging TAuthorityModule instance");
		try {
			TAuthorityModule result = (TAuthorityModule) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TAuthorityModule instance) {
		log.debug("attaching dirty TAuthorityModule instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TAuthorityModule instance) {
		log.debug("attaching clean TAuthorityModule instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}