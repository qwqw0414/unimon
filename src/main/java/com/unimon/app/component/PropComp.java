package com.unimon.app.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.vo.PickPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PropComp {

	public String get(List<PickPoint> list) throws Exception {
		
		if(list == null || list.size() == 0)
			throw new AppException("Exist List");
		
		int sum = 0;
		List<String> resultList = new ArrayList<>();
		
		for(PickPoint i : list) {
			sum += i.getRarity();
			
			for(int n = 0; n < i.getRarity(); n++) {
				resultList.add(i.getRare());
			}
		}
		
		if(sum == 0)
			throw new AppException("Exist Rarity Score");
		
		int target = (int)(Math.random() * sum );
		
		return resultList.get(target);
	}
	
	public List<String> get(List<PickPoint> list, int amount) throws Exception {
		
		List<String> result = new ArrayList<>();
		
		for(int i = 0; i < amount; i++) {
			result.add(this.get(list));
		}
		
		return result;
	}
	
}
