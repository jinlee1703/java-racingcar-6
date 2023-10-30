package racingcar;

import static camp.nextstep.edu.missionutils.Console.*;
import static camp.nextstep.edu.missionutils.Randoms.*;

import java.util.ArrayList;
import java.util.List;

public class Race {
	private List<Car> carList = new ArrayList<>();
	private int turnCount = 0;
	private static int CAR_NAME_MAXIMUM_LENGTH = 5;

	public void start() {
		setCarList();
		setTurnCount();
		System.out.println();
		for (int i = 0; i < turnCount; i++) {
			executeTurn();
		}
		printResult();
	}

	private void setCarList() {
		System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
		String input = readLine().trim();
		for (String carName : input.split(",")) {
			if (carName.length() == 0 || carName.length() > CAR_NAME_MAXIMUM_LENGTH) {
				throw new IllegalArgumentException("자동차 이름이 올바르지 않습니다.");
			}
			carList.add(new Car(carName));
		}
	}

	private void setTurnCount() {
		System.out.println("시도할 회수는 몇회인가요?");
		String input = readLine().trim();
		try {
			turnCount = Integer.parseInt(input.trim());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자만 입력 가능합니다.");
		}
	}

	private void executeTurn() {
		carList.forEach(car -> car.run(pickNumberInRange(0, 9)));
	}

	private void printResult() {
		List<String> winnerList = new ArrayList<>();
		int maxValue = carList.stream()
				.mapToInt(Car::getMoveCount)
				.max()
				.orElse(0);

		carList.forEach(car -> {
			if (car.getMoveCount() == maxValue) {
				winnerList.add(car.getName());
			}
		});
		System.out.println("최종 우승자 : ".concat(String.join(", ", winnerList)));
	}
}
