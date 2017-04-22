
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

import com.pzy.entity.MsgBox;
import com.pzy.repository.MsgBoxRepository;

@Service
public class MsgBoxService {
	
    @Autowired
    private MsgBoxRepository msgBoxRepository;
    public List<MsgBox> findAll() {
         return (List<MsgBox>) msgBoxRepository.findAll();
    }
    public List<MsgBox> findMsgBoxSubs() {
        return (List<MsgBox>) msgBoxRepository.findAll();
    }
    public List<MsgBox> findMsgBoxs() {
        return (List<MsgBox>) msgBoxRepository.findAll();
    }
    
    public Page<MsgBox> findAll(final int pageNumber, final int pageSize,final String name){
        PageRequest pageRequest = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "id"));
       
        Specification<MsgBox> spec = new Specification<MsgBox>() {
             public Predicate toPredicate(Root<MsgBox> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
             Predicate predicate = cb.conjunction();
             if (name != null) {
                  predicate.getExpressions().add(cb.like(root.get("title").as(String.class), name+"%"));
             }
             return predicate;
             }
        };
        Page<MsgBox> result = (Page<MsgBox>) msgBoxRepository.findAll(spec, pageRequest);
        return result;
    	}
		public void delete(Long id){
			msgBoxRepository.delete(id);
		}
		public MsgBox findMsgBox(Long id){
			  return msgBoxRepository.findOne(id);
		}
		public MsgBox find(Long id){
			  return msgBoxRepository.findOne(id);
		}
		public void save(MsgBox msgBox){
			msgBoxRepository.save(msgBox);
		}
}