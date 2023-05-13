import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TruthTableUtil {
	private final List<Boolean> possible= List.of(true,false); 
	private Boolean[] mutablesItems;
	private Function<List<Boolean>,Boolean> expFunc;
	private int lineNum;
	private List<Boolean> resList=new ArrayList<>();
	
	public static void main(String[] args) {
		new TruthTableUtil()
			.truthTableGeneration(new Boolean[4],
					a->(a.get(0)||a.get(1)||a.get(2))&&(a.get(0)||a.get(3))&&(a.get(1)||a.get(3))&&(a.get(2)||a.get(3)));
	}

	
	
	/**
	 *  生成真值表
	 *  
	 * @param mutablesItems 可变元数组（指定大小的空数组）
	 * @param expFunc
	 */
	public void truthTableGeneration(Boolean[] mutablesItems,Function<List<Boolean>,Boolean> expFunc) {
		this.mutablesItems=mutablesItems;
		this.expFunc=expFunc;
		this.lineNum=0;
		System.out.println(printTableHeader(mutablesItems));
		recursive(mutablesItems.length);
		printDisaggregationParadigm(resList);
	}
	
	/**
	 * 生成析取范式
	 * 
	 * @param resList 结果表
	 */
	private void printDisaggregationParadigm(List<Boolean> resList) {
		
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < resList.size(); i++) {
			int seq=i%mutablesItems.length;
			
			if(seq==0) {
				stringBuilder.append(")||(");
			}
			
			if(seq!=0) {
				stringBuilder.append("&&");
			}
			
			Boolean boolean1 = resList.get(i);
			if(boolean1) {
				stringBuilder.append(seq);
			}else {
				stringBuilder.append("!").append(seq);
			}
		}
		
		stringBuilder.append(")");
		stringBuilder.delete(0, 3);
		
		
		System.out.println(stringBuilder);
		
	}



	/**
	 * 
	 * 递归计算不同变元值下的表达式值
	 * 
	 * @param index 当前改变的变元索引
	 */
	private void recursive(int index) {
		if(0==index) {
			List<Boolean> list = Arrays.stream(mutablesItems).collect(Collectors.toList());
			boolean exp=expFunc.apply(list);
			System.out.println(printReport(mutablesItems,exp));
			if(exp) {
				resList.addAll(list);
			}
		}
		if(index<=0) {
			return;
		}
		
		for(boolean curValue0:possible) {
			mutablesItems[3-(index-1)]=curValue0;
			recursive(index-1);
		}
	}
	
	/**
	 * 打印真值行
	 * @param mutablesItems 可变元数组
	 * @param exp 布尔表达式
	 * @return 真值表
	 */
	private String printReport(Boolean[] mutablesItems,boolean exp) {
		StringBuilder stringBuilder = new StringBuilder((lineNum++)+"\t");
		for(int i=0;i<mutablesItems.length;i++) {
			stringBuilder.append(mutablesItems[i]).append("\t");
		}
		stringBuilder.append(exp).append("\t");
		return stringBuilder.toString();
	}
	/**
	 * 打印真值表头
	 * @param mutablesItems 可变元数组
	 * @param exp 布尔表达式
	 * @return 真值表
	 */
	private String printTableHeader(Boolean[] mutablesItems) {
		StringBuilder stringBuilder = new StringBuilder("lineNum\t");
		for(int i=0;i<mutablesItems.length;i++) {
			stringBuilder.append(i).append("\t");
		}
		stringBuilder.append("exp").append("\t");
		return stringBuilder.toString();
	}
}
