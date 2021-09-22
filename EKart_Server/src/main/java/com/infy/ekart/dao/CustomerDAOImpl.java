package com.infy.ekart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.infy.ekart.entity.AddressEntity;
import com.infy.ekart.entity.CustomerCartEntity;
import com.infy.ekart.entity.CustomerEntity;
import com.infy.ekart.model.Address;
import com.infy.ekart.model.Customer;
import com.infy.ekart.model.CustomerCart;
import com.infy.ekart.model.Product;

@Repository(value = "customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String authenticateCustomer(String emailId, String password){
		
		Query query = entityManager.createQuery("select c from CustomerEntity c where c.emailId = '"+emailId+"' and c.password = '"+password+"'");
		
		List<CustomerEntity> customerEntities = query.getResultList();
		if(customerEntities.isEmpty())
			return null;

		return customerEntities.get(0).getEmailId();
	}
	
	@Override
	public String getPasswordOfCustomer(String emailId) {
		
		String password = null;
		emailId = emailId.toLowerCase();
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, emailId);
		if (customerEntity!=null){
			password = customerEntity.getPassword();
		}
		
		return password;
	}


	@Override
	public Customer getCustomerByEmailId(String emailId) {

		Customer customer = null;
		emailId = emailId.toLowerCase();
		List<Address> customerAddresses = new ArrayList<>();
		
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, emailId);
		if (customerEntity!=null){
			customer = new Customer();
			customer.setEmailId(customerEntity.getEmailId());
			customer.setName(customerEntity.getName());
			customer.setPhoneNumber(customerEntity.getPhoneNumber());
			for (AddressEntity i : customerEntity.getAddressEntities()) {
				Address address = new Address();
				address.setAddressId(i.getAddressId());
				address.setAddressLine1(i.getAddressLine1());
				address.setAddressLine2(i.getAddressLine2());
				address.setCity(i.getCity());
				address.setContactNumber(i.getContactNumber());
				address.setPin(i.getPin());
				address.setState(i.getState());
				
				customerAddresses.add(address);
			}
			customer.setAddresses(customerAddresses);
			
			List<CustomerCart> customerCarts = new ArrayList<>();
			for (CustomerCartEntity customerCartEntity : customerEntity.getCustomerCarts()) {	
				CustomerCart cart = new CustomerCart();
				cart.setCartId(customerCartEntity.getCartId());
				cart.setQuantity(customerCartEntity.getQuantity());
					Product product = new Product();
					product.setBrand(customerCartEntity.getProductEntity().getBrand());
					product.setCategory(customerCartEntity.getProductEntity().getCategory());
					product.setDescription(customerCartEntity.getProductEntity().getDescription());
					product.setDiscount(customerCartEntity.getProductEntity().getDiscount());
					product.setName(customerCartEntity.getProductEntity().getName());
					product.setPrice(customerCartEntity.getProductEntity().getPrice());
					product.setProductId(customerCartEntity.getProductEntity().getProductId());
					product.setQuantity(customerCartEntity.getProductEntity().getQuantity());
				
				cart.setProduct(product);
				
				customerCarts.add(cart);
			}
			customer.setCustomerCarts(customerCarts);
			
		}
		
		return customer;
	}
	
	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		
		Boolean flag = false;

		CustomerEntity customerEntity = null;

		customerEntity = entityManager.find(CustomerEntity.class, emailId);

		if(customerEntity == null)
			flag = true;

		return flag;
	}

	@Override
	public Boolean checkAvailabilityOfPhoneNumber(String phoneNumber) {
		
		Boolean flag = false;

		Query query = entityManager.createQuery("select c from CustomerEntity c where c.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<CustomerEntity> customerEntities = query.getResultList();
		
		if(customerEntities.isEmpty())
			flag = true;

		return flag;
	}

	@Override
	public String registerNewCustomer(Customer customer) {
		
		String registeredWithEmailId = null;

		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setEmailId(customer.getEmailId());
		customerEntity.setName(customer.getName());
		customerEntity.setPassword(customer.getPassword());
		customerEntity.setPhoneNumber(customer.getPhoneNumber());
		
		entityManager.persist(customerEntity);
		
		registeredWithEmailId = customerEntity.getEmailId();
		
		return registeredWithEmailId;
	}
	
	
	@Override
	public Customer getCustomerByPhoneNumber(String phoneNumber) {
		
		CustomerEntity customerEntity = null;
		Customer customer=null;

		Query query = entityManager.createQuery("select c from CustomerEntity c where c.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<CustomerEntity> customerEntities = query.getResultList();
		
		if(!customerEntities.isEmpty()){
			customerEntity = customerEntities.get(0);
			customer = new Customer();
			
			customer.setEmailId(customerEntity.getEmailId());
			customer.setName(customerEntity.getName());
			customer.setPhoneNumber(customerEntity.getPhoneNumber());
			List<Address> addList=new ArrayList<>();
			for(AddressEntity ae:customerEntity.getAddressEntities()){
				Address a=new Address();
				a.setAddressId(ae.getAddressId());
				a.setAddressLine1(ae.getAddressLine1());
				a.setAddressLine2(ae.getAddressLine2());
				a.setCity(ae.getCity());
				a.setContactNumber(ae.getContactNumber());
				a.setPin(ae.getPin());
				a.setState(ae.getState());
				addList.add(a);
			}
			customer.setAddresses(addList);
			
			List<CustomerCart> customerCarts = new ArrayList<>();
			for (CustomerCartEntity customerCartEntity : customerEntity.getCustomerCarts()) {	
				CustomerCart cart = new CustomerCart();
				cart.setCartId(customerCartEntity.getCartId());
				cart.setQuantity(customerCartEntity.getQuantity());
					Product product = new Product();
					product.setBrand(customerCartEntity.getProductEntity().getBrand());
					product.setCategory(customerCartEntity.getProductEntity().getCategory());
					product.setDescription(customerCartEntity.getProductEntity().getDescription());
					product.setDiscount(customerCartEntity.getProductEntity().getDiscount());
					product.setName(customerCartEntity.getProductEntity().getName());
					product.setPrice(customerCartEntity.getProductEntity().getPrice());
					product.setProductId(customerCartEntity.getProductEntity().getProductId());
					product.setQuantity(customerCartEntity.getProductEntity().getQuantity());
				
				cart.setProduct(product);
				
				customerCarts.add(cart);
			}
			customer.setCustomerCarts(customerCarts);
		}
			
		return customer; 
	}
	
	@Override
	public void updateProfile(Customer customer) {

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customer.getEmailId().toLowerCase());
		
		customerEntity.setName(customer.getName());
		customerEntity.setPhoneNumber(customer.getPhoneNumber());
		
	}
	
	@Override
	public void changePassword(String customerEmailId, String newHashedPassword) {

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		
		customerEntity.setPassword(newHashedPassword);

	}
	
	
	@Override
	public Integer addShippingAddress(String customerEmailId, Address address) {
		
		CustomerEntity customerEntity = null;
		
		Integer newAddressId = null;
		
		customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		
		List<AddressEntity> customerAddressEntities = customerEntity.getAddressEntities();
		
		AddressEntity newShippingAddress = new AddressEntity();
		newShippingAddress.setAddressLine1(address.getAddressLine1());
		newShippingAddress.setAddressLine2(address.getAddressLine2());
		newShippingAddress.setCity(address.getCity());
		newShippingAddress.setContactNumber(address.getContactNumber());
		newShippingAddress.setPin(address.getPin());
		newShippingAddress.setState(address.getState());
		
		customerAddressEntities.add(newShippingAddress);
		customerEntity.setAddressEntities(customerAddressEntities);
		
		entityManager.persist(customerEntity);
		
		
		List<AddressEntity> customerAddressEntitiesAfterAddition = customerEntity.getAddressEntities();
		
		AddressEntity newAddress = customerAddressEntitiesAfterAddition.get(customerAddressEntitiesAfterAddition.size()-1);
		newAddressId = newAddress.getAddressId();
		return newAddressId;
		
	}
	
	@Override
	public void updateShippingAddress(Address address) {

		AddressEntity addressEntity=entityManager.find(AddressEntity.class, address.getAddressId());
		addressEntity.setAddressLine1(address.getAddressLine1());
		addressEntity.setAddressLine2(address.getAddressLine2());
		addressEntity.setCity(address.getCity());
		addressEntity.setContactNumber(address.getContactNumber());
		addressEntity.setPin(address.getPin());
		addressEntity.setState(address.getState());
		
	}

	@Override
	public void deleteShippingAddress(String customerEmailId, Integer addressId) {

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
	       List<AddressEntity> customerAddressEntities = customerEntity.getAddressEntities();
	       AddressEntity addressEntity = entityManager.find(AddressEntity.class, addressId);
	       
	       customerAddressEntities.remove(addressEntity);
	}
	@Override
	   public Address getShippingAddress(Integer addressId) throws Exception {
	       AddressEntity addressEntity = entityManager.find(AddressEntity.class, addressId);
	       Address address = new Address();
	       address.setAddressId(addressEntity.getAddressId());
	       address.setAddressLine1(addressEntity.getAddressLine1());
	       address.setAddressLine2(addressEntity.getAddressLine2());
	       address.setCity(addressEntity.getCity());
	       address.setContactNumber(addressEntity.getContactNumber());
	       address.setPin(addressEntity.getPin());
	       address.setState(addressEntity.getState());
	       
	       return address;
	       
	   }
}
