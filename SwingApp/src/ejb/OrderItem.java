package ejb;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

   /** 
   Class generated using Kroki EJBGenerator 
   @Author mrd 
   Creation date: 14.06.2013  13:02:06h
   **/

@Entity
@Table(name = "WS_ORDER_ITEM")
public class OrderItem implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private java.lang.Long id;

	@Column(name = "OI_ORDERED_QUANTITY", unique = false, nullable = false)
	private java.lang.String orderedQuantity;
	@Column(name = "OI_AVAILABLE", unique = false, nullable = false)
	private java.lang.Boolean available;
	@Column(name = "OI_UNIT_PRICE", unique = false, nullable = false)
	private java.math.BigDecimal unitPrice;
	@Column(name = "OI_UNIT_TAX", unique = false, nullable = false)
	private java.math.BigDecimal unitTax;
	@Column(name = "OI_VALUE", unique = false, nullable = false)
	private java.math.BigDecimal value;
	@ManyToOne
	@JoinColumn(name="orderitem_order", referencedColumnName="ID",  nullable = true)
	private Order orderitem_order;
	@ManyToOne
	@JoinColumn(name="orderitem_product", referencedColumnName="ID",  nullable = true)
	private Product orderitem_product;
	
	public OrderItem(){
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public java.lang.String getOrderedQuantity() {
		return this.orderedQuantity;
	}
	
	public void setOrderedQuantity(java.lang.String orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	public java.lang.Boolean getAvailable() {
		return this.available;
	}
	
	public void setAvailable(java.lang.Boolean available) {
		this.available = available;
	}
	
	public java.math.BigDecimal getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitPrice(java.math.BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public java.math.BigDecimal getUnitTax() {
		return this.unitTax;
	}
	
	public void setUnitTax(java.math.BigDecimal unitTax) {
		this.unitTax = unitTax;
	}
	
	public java.math.BigDecimal getValue() {
		return this.value;
	}
	
	public void setValue(java.math.BigDecimal value) {
		this.value = value;
	}
	
	public Order getOrderitem_order() {
		return this.orderitem_order;
	}
	
	public void setOrderitem_order(Order orderitem_order) {
		this.orderitem_order = orderitem_order;
	}
	
	public Product getOrderitem_product() {
		return this.orderitem_product;
	}
	
	public void setOrderitem_product(Product orderitem_product) {
		this.orderitem_product = orderitem_product;
	}
	
}