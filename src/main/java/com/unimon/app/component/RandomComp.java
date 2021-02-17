package com.unimon.app.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.unimon.app.common.exception.AppException;
import com.unimon.app.vo.PickPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * 데이터 랜덤 출력 컴포넌트
 * @author SamSung
 *
 */
@Slf4j
@Component(value = "RandomComp")
public class RandomComp {

	/**
	 * 리스트 데이터에서 랜덤한 값 출력
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getList(List<Map<String, Object>> list) throws Exception {
		return list.get((int)(Math.random() * list.size()));
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public String getRare(List<PickPoint> list) throws Exception {
		
		if(list == null || list.size() == 0)
			throw new AppException("Exist List");
		
		List<String> resultList = new ArrayList<>();
		int sum = 0;
		
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
			result.add(this.getRare(list));
		}
		
		return result;
	}
	
}
