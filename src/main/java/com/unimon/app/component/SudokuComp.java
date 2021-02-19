package com.unimon.app.component;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.NumberOfInterveningJobs;

import org.springframework.stereotype.Component;

import com.unimon.app.common.exception.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SudokuComp {

	private final int MAX_TRY = 1000;

	public int[][] create(int freq) throws RuntimeException {
		
		int[][] arr = null;
		int cnt = 0;
		
		while (cnt++ < MAX_TRY*100) {

			arr = getBase(freq);

			int[][] test = clone(arr);
			try {
				solve(test);
			} catch (AppException e) {
				continue;
			}
			return arr;
		}
		
		throw new AppException("Create Falied : OverTry");
	}
	
	private int[][] getBase(int freq) throws RuntimeException {
		
		int[][] arr = new int[9][9];
		
		List<Integer> numList = null;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j <9; j++) {

				int num = 0;
				
				if( ((int)(Math.random() * freq)) != 0) {
					
					numList = new ArrayList<>();
					
					for(int n = 1; n <= 9; n++) {
						if(isValidAll(arr, n, i, j)) {
							numList.add(n);
						}
					}
					
					if(numList.size() > 1) {
						num = numList.get((int)(Math.random() * numList.size()));
					}
				}
				arr[i][j] = num;
			}
		}
		
		return arr;
	}
	
	public int[][] solve(int[][] arr) throws RuntimeException {

		if (!isValid(arr))
			throw new AppException("invalid data");

		linerSolve(arr);

		if (countEmpty(arr) == 0)
			return arr;
		
		int[][] clone = null;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int n = 1; n <= 9; n++) {

					if (isValidAll(arr, n, i, j)) {
						clone = clone(arr);
						clone[i][j] = n;
						linerSolve(clone);
						
						if (countEmpty(clone) == 0)
							return clone;
					}

				}
			}
		}

//		return arr;
		throw new AppException("Solve Failed");
	}

	private void linerSolve(int[][] arr) throws RuntimeException {

		int step = 0;
		int temp = -1;
		int empty = 0;
		boolean sw = false;

		while (true) {

			if (sw)
				setPrimaryNum(arr);
			else
				setUniqueNum(arr);

			empty = countEmpty(arr);

			if (sw && temp == empty)
				break;
			
			sw = temp == empty;

			temp = empty;

			
			if (empty == 0)
				break;
			
			if (step++ > MAX_TRY)
				break;
		}
	}

	private boolean isValidRow(int[][] arr, int num, int y) {

		for (int i = 0; i < 9; i++) {
			if (arr[y][i] == num)
				return false;
		}
		return true;
	}

	private boolean isValidCol(int[][] arr, int num, int x) {

		for (int i = 0; i < 9; i++) {
			if (arr[i][x] == num)
				return false;
		}
		return true;
	}

	private boolean isValidBox(int[][] arr, int num, int y, int x) {

		y /= 3;
		x /= 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i + y * 3][j + x * 3] == num)
					return false;
			}
		}
		return true;
	}

	private boolean isValidAll(int[][] arr, int num, int y, int x) {
		if (arr[y][x] != 0) {
			return false;
		}
		if (isValidCol(arr, num, x) && isValidRow(arr, num, y) && isValidBox(arr, num, y, x)) {
			return true;
		}
		return false;
	}

	private void setUniqueNum(int[][] arr) {

		boolean isValid = false;
		int temp = 0;

		for (int i = 0; i < 9; i++) {
			col: for (int j = 0; j < 9; j++) {
				if (arr[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (!isValid && isValidAll(arr, k, i, j)) {
							temp = k;
							isValid = true;
						} else if (isValid && isValidAll(arr, k, i, j)) {
							isValid = false;
							continue col;
						}
					}
					if (isValid) {
						arr[i][j] = temp;
						isValid = false;
					}
				}
			}
		}
	}

	private boolean isUniqueRow(String[][] arr, int num, int y) {
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			if (arr[y][i] != null && arr[y][i].contains(String.valueOf(num))) {
				cnt++;
			}
		}
		return cnt == 1;
	}

	private boolean isUniqueCol(String[][] arr, int num, int x) {
		int cnt = 0;
		for (int i = 0; i < 9; i++) {
			if (arr[i][x] != null && arr[i][x].contains(String.valueOf(num))) {
				cnt++;
			}
		}
		return cnt == 1;
	}

	private boolean isUniqueBox(String[][] arr, int num, int y, int x) {

		int cnt = 0;
		String temp;

		y /= 3;
		x /= 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp = arr[i + y * 3][j + x * 3];
				if (temp != null && temp.contains(String.valueOf(num)))
					cnt++;
			}
		}

		return cnt == 1;
	}

	private boolean isUniqueAll(String[][] arr, int num, int y, int x) {

		if (arr[y][x] == null || !(arr[y][x].contains(String.valueOf(num))))
			return false;

		return (isUniqueCol(arr, num, x) || isUniqueRow(arr, num, y) || isUniqueBox(arr, num, y, x));
	}

	public String[][] getValidNumArray(int[][] arr) {

		String[][] result = new String[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k = 1; k <= 9; k++) {
					if (isValidAll(arr, k, i, j)) {
						if (result[i][j] == null || result[i][j].length() == 0) {
							result[i][j] = String.valueOf(k);
						} else {
							result[i][j] += String.valueOf(k);
						}
					}
				}
			}
		}
		return result;
	}

	private void setPrimaryNum(int[][] arr) {
		String[][] validList = getValidNumArray(arr);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(arr[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (isUniqueAll(validList, k, i, j)) {
							arr[i][j] = k;
							break;
						}
					}
				}
			}
		}
	}

	public int countEmpty(int[][] arr) {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	public boolean isValid(int[][] arr) {

		if (arr == null || arr.length != 9)
			return false;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null || arr[i].length != 9)
				return false;
		}

		return true;
	}

	public int[][] clone(int[][] arr) throws RuntimeException {

		if (!isValid(arr))
			throw new AppException("Invalid Data");

		int[][] clone = new int[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				clone[i][j] = arr[i][j];
			}
		}

		return clone;
	}

	public String getString(int[][] arr) {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				result += arr[i][j];
			}
		}
		return result;
	}

	public void print(int[][] arr) {
		for (int i = 0; i < 9; i++) {
			log.debug("{} . {}", i + 1, arr[i]);
		}
	}
	
	public void print(String[][] arr) {
		for (int i = 0; i < 9; i++) {
			log.debug("{} . {}", i + 1, arr[i]);
		}
	}
	
}
