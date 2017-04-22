
package com.pzy.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pzy.entity.Cart;
import com.pzy.entity.Order;
import com.pzy.entity.User;
import com.pzy.repository.CartRepository;

@Service
public class CartService {
	
    @Autowired
    private CartRepository cartRepository;
    public List<Cart> findAll() {
         return (List<Cart>) cartRepository.findAll();
    }
    public List<Cart> findCartSubs() {
        return (List<Cart>) cartRepository.findAll();
    }
    public List<Cart> findCarts() {
        return (List<Cart>) cartRepository.findAll();
    }
    public List<Cart> findByOrder(Order order){
		return cartRepository.findByOrder(order);
	}
    public Page<Cart> findAll(final int pageNumber, final int pageSize,final String state){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
       
        Specification<Cart> spec = new Specification<Cart>() {
             public Predicate toPredicate(Root<Cart> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (state != null) {
                  predicate.getExpressions().add(cb.equal(root.get("state").as(String.class), state));
             }
             return predicate;
             }
        };
        Page<Cart> result = (Page<Cart>) cartRepository.findAll(spec, pageRequest);
        return result;
    	}
		public void delete(Long id){
			cartRepository.delete(id);
		}
		public Cart findCart(Long id){
			  return cartRepository.findOne(id);
		}
		public Cart find(Long id){
			  return cartRepository.findOne(id);
		}
		public void save(Cart cart){
			cartRepository.save(cart);
		}
}