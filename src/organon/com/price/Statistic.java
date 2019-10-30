package organon.com.price;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;

public class Statistic {

	public static void main(String[] args) {
		Map<Double, Integer> map = new HashMap<>();
		map.put(0.7, 100);
		map.put(0.5, 200);

		double weightedAverage = map.entrySet().stream()
				.collect(averagingWeighted(Map.Entry::getKey, Map.Entry::getValue));

		System.out.println(weightedAverage);
	}

	public static <T> Collector<T, ?, Double> averagingWeighted(ToDoubleFunction<T> valueFunction,
			ToIntFunction<T> weightFunction) {
		class Box {
			double num = 0;
			long denom = 0;
		}
		return Collector.of(Box::new, (b, e) -> {
			b.num += valueFunction.applyAsDouble(e) * weightFunction.applyAsInt(e);
			b.denom += weightFunction.applyAsInt(e);
		}, (b1, b2) -> {
			b1.num += b2.num;
			b1.denom += b2.denom;
			return b1;
		}, b -> b.num / b.denom);
	}

	// The bid-ask spread is a reflection of the supply and demand for a particular
	// asset.
	// The bid represents demand and the ask represents supply for an asset. The
	// depth of the "bids" and the "asks" can have a significant impact on the
	// bid-ask spread, making it widen significantly if one outweighs the other or
	// if both are not robust. Market makers and traders make money by exploiting
	// the bid-ask spread and the depth of bids and asks to net the spread
	// difference.
	//
	public static double getBidAskSpread(List<Price> listPrice) {
		double numerator = 0.0;
		double denominatior = 0.0;
		for (Price price : listPrice) {
			
		}
	
	}

}

class Price {
	int id;
	int bidSize;
	double bidPrice;
	int askSize;
	double askPrice;
}
