/**
 */
package org.geckoprojects.emf.mongo.model;

import org.bson.BsonDocument;
import org.bson.Document;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EMongo Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getProjection <em>Projection</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getProjectionOnly <em>Projection Only</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getSort <em>Sort</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getSkip <em>Skip</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getBatchSize <em>Batch Size</em>}</li>
 * </ul>
 *
 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery()
 * @model kind="class"
 * @generated
 */
public class EMongoQuery extends MinimalEObjectImpl.Container implements EObject {
	/**
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final BsonDocument FILTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected BsonDocument filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjection() <em>Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjection()
	 * @generated
	 * @ordered
	 */
	protected static final Document PROJECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjection() <em>Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjection()
	 * @generated
	 * @ordered
	 */
	protected Document projection = PROJECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getProjectionOnly() <em>Projection Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectionOnly()
	 * @generated
	 * @ordered
	 */
	protected static final Document PROJECTION_ONLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProjectionOnly() <em>Projection Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProjectionOnly()
	 * @generated
	 * @ordered
	 */
	protected Document projectionOnly = PROJECTION_ONLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSort() <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSort()
	 * @generated
	 * @ordered
	 */
	protected static final Document SORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSort() <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSort()
	 * @generated
	 * @ordered
	 */
	protected Document sort = SORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSkip() <em>Skip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkip()
	 * @generated
	 * @ordered
	 */
	protected static final Integer SKIP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSkip() <em>Skip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkip()
	 * @generated
	 * @ordered
	 */
	protected Integer skip = SKIP_EDEFAULT;

	/**
	 * The default value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected Integer limit = LIMIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getBatchSize() <em>Batch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatchSize()
	 * @generated
	 * @ordered
	 */
	protected static final Integer BATCH_SIZE_EDEFAULT = new Integer(1000);

	/**
	 * The cached value of the '{@link #getBatchSize() <em>Batch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBatchSize()
	 * @generated
	 * @ordered
	 */
	protected Integer batchSize = BATCH_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMongoQuery() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EMONGO_QUERY;
	}

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' attribute.
	 * @see #setFilter(BsonDocument)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_Filter()
	 * @model unique="false" dataType="org.geckoprojects.emf.mongo.model.EBsonDocument"
	 * @generated
	 */
	public BsonDocument getFilter() {
		return filter;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getFilter <em>Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newFilter the new value of the '<em>Filter</em>' attribute.
	 * @see #getFilter()
	 * @generated
	 */
	public void setFilter(BsonDocument newFilter) {
		BsonDocument oldFilter = filter;
		filter = newFilter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__FILTER, oldFilter, filter));
	}

	/**
	 * Returns the value of the '<em><b>Projection</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projection</em>' attribute.
	 * @see #setProjection(Document)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_Projection()
	 * @model unique="false" dataType="org.geckoprojects.emf.mongo.model.EDocument"
	 * @generated
	 */
	public Document getProjection() {
		return projection;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getProjection <em>Projection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newProjection the new value of the '<em>Projection</em>' attribute.
	 * @see #getProjection()
	 * @generated
	 */
	public void setProjection(Document newProjection) {
		Document oldProjection = projection;
		projection = newProjection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__PROJECTION, oldProjection, projection));
	}

	/**
	 * Returns the value of the '<em><b>Projection Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Projection Only</em>' attribute.
	 * @see #setProjectionOnly(Document)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_ProjectionOnly()
	 * @model unique="false" dataType="org.geckoprojects.emf.mongo.model.EDocument"
	 * @generated
	 */
	public Document getProjectionOnly() {
		return projectionOnly;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getProjectionOnly <em>Projection Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newProjectionOnly the new value of the '<em>Projection Only</em>' attribute.
	 * @see #getProjectionOnly()
	 * @generated
	 */
	public void setProjectionOnly(Document newProjectionOnly) {
		Document oldProjectionOnly = projectionOnly;
		projectionOnly = newProjectionOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__PROJECTION_ONLY, oldProjectionOnly, projectionOnly));
	}

	/**
	 * Returns the value of the '<em><b>Sort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sort</em>' attribute.
	 * @see #setSort(Document)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_Sort()
	 * @model unique="false" dataType="org.geckoprojects.emf.mongo.model.EDocument"
	 * @generated
	 */
	public Document getSort() {
		return sort;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getSort <em>Sort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newSort the new value of the '<em>Sort</em>' attribute.
	 * @see #getSort()
	 * @generated
	 */
	public void setSort(Document newSort) {
		Document oldSort = sort;
		sort = newSort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__SORT, oldSort, sort));
	}

	/**
	 * Returns the value of the '<em><b>Skip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Skip</em>' attribute.
	 * @see #setSkip(Integer)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_Skip()
	 * @model unique="false"
	 * @generated
	 */
	public Integer getSkip() {
		return skip;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getSkip <em>Skip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newSkip the new value of the '<em>Skip</em>' attribute.
	 * @see #getSkip()
	 * @generated
	 */
	public void setSkip(Integer newSkip) {
		Integer oldSkip = skip;
		skip = newSkip;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__SKIP, oldSkip, skip));
	}

	/**
	 * Returns the value of the '<em><b>Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Limit</em>' attribute.
	 * @see #setLimit(Integer)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_Limit()
	 * @model unique="false"
	 * @generated
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getLimit <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newLimit the new value of the '<em>Limit</em>' attribute.
	 * @see #getLimit()
	 * @generated
	 */
	public void setLimit(Integer newLimit) {
		Integer oldLimit = limit;
		limit = newLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__LIMIT, oldLimit, limit));
	}

	/**
	 * Returns the value of the '<em><b>Batch Size</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Batch Size</em>' attribute.
	 * @see #setBatchSize(Integer)
	 * @see org.geckoprojects.emf.mongo.model.ModelPackage#getEMongoQuery_BatchSize()
	 * @model default="1000" unique="false"
	 * @generated
	 */
	public Integer getBatchSize() {
		return batchSize;
	}

	/**
	 * Sets the value of the '{@link org.geckoprojects.emf.mongo.model.EMongoQuery#getBatchSize <em>Batch Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newBatchSize the new value of the '<em>Batch Size</em>' attribute.
	 * @see #getBatchSize()
	 * @generated
	 */
	public void setBatchSize(Integer newBatchSize) {
		Integer oldBatchSize = batchSize;
		batchSize = newBatchSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EMONGO_QUERY__BATCH_SIZE, oldBatchSize, batchSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.EMONGO_QUERY__FILTER:
				return getFilter();
			case ModelPackage.EMONGO_QUERY__PROJECTION:
				return getProjection();
			case ModelPackage.EMONGO_QUERY__PROJECTION_ONLY:
				return getProjectionOnly();
			case ModelPackage.EMONGO_QUERY__SORT:
				return getSort();
			case ModelPackage.EMONGO_QUERY__SKIP:
				return getSkip();
			case ModelPackage.EMONGO_QUERY__LIMIT:
				return getLimit();
			case ModelPackage.EMONGO_QUERY__BATCH_SIZE:
				return getBatchSize();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.EMONGO_QUERY__FILTER:
				setFilter((BsonDocument)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__PROJECTION:
				setProjection((Document)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__PROJECTION_ONLY:
				setProjectionOnly((Document)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__SORT:
				setSort((Document)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__SKIP:
				setSkip((Integer)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__LIMIT:
				setLimit((Integer)newValue);
				return;
			case ModelPackage.EMONGO_QUERY__BATCH_SIZE:
				setBatchSize((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.EMONGO_QUERY__FILTER:
				setFilter(FILTER_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__PROJECTION:
				setProjection(PROJECTION_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__PROJECTION_ONLY:
				setProjectionOnly(PROJECTION_ONLY_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__SORT:
				setSort(SORT_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__SKIP:
				setSkip(SKIP_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__LIMIT:
				setLimit(LIMIT_EDEFAULT);
				return;
			case ModelPackage.EMONGO_QUERY__BATCH_SIZE:
				setBatchSize(BATCH_SIZE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.EMONGO_QUERY__FILTER:
				return FILTER_EDEFAULT == null ? filter != null : !FILTER_EDEFAULT.equals(filter);
			case ModelPackage.EMONGO_QUERY__PROJECTION:
				return PROJECTION_EDEFAULT == null ? projection != null : !PROJECTION_EDEFAULT.equals(projection);
			case ModelPackage.EMONGO_QUERY__PROJECTION_ONLY:
				return PROJECTION_ONLY_EDEFAULT == null ? projectionOnly != null : !PROJECTION_ONLY_EDEFAULT.equals(projectionOnly);
			case ModelPackage.EMONGO_QUERY__SORT:
				return SORT_EDEFAULT == null ? sort != null : !SORT_EDEFAULT.equals(sort);
			case ModelPackage.EMONGO_QUERY__SKIP:
				return SKIP_EDEFAULT == null ? skip != null : !SKIP_EDEFAULT.equals(skip);
			case ModelPackage.EMONGO_QUERY__LIMIT:
				return LIMIT_EDEFAULT == null ? limit != null : !LIMIT_EDEFAULT.equals(limit);
			case ModelPackage.EMONGO_QUERY__BATCH_SIZE:
				return BATCH_SIZE_EDEFAULT == null ? batchSize != null : !BATCH_SIZE_EDEFAULT.equals(batchSize);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (filter: ");
		result.append(filter);
		result.append(", projection: ");
		result.append(projection);
		result.append(", projectionOnly: ");
		result.append(projectionOnly);
		result.append(", sort: ");
		result.append(sort);
		result.append(", skip: ");
		result.append(skip);
		result.append(", limit: ");
		result.append(limit);
		result.append(", batchSize: ");
		result.append(batchSize);
		result.append(')');
		return result.toString();
	}

} // EMongoQuery