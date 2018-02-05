package vmd;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import dto.CustomerDto;
import dto.EmployeeDto;
import dto.ProductDto;
import service.CustomerSvc;
import service.ProductSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CustomerVmd {
	@WireVariable
	private CustomerSvc customerSvc;
	
	private List<CustomerDto> customerDtos;
	private CustomerDto customerDto;
	public CustomerSvc getCustomerSvc() {
		return customerSvc;
	}
	public void setCustomerSvc(CustomerSvc customerSvc) {
		this.customerSvc = customerSvc;
	}
	public List<CustomerDto> getCustomerDtos() {
		return customerDtos;
	}
	public void setCustomerDtos(List<CustomerDto> customerDtos) {
		this.customerDtos = customerDtos;
	}
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	
	@Init
	public void load()
	{
		customerDtos=customerSvc.findAll();
		
	}
	
	@Command
	public void add()
	{
		customerDto= new CustomerDto();
		Sessions.getCurrent().setAttribute("dto", customerDto);
		Executions.sendRedirect("customer_detail.zul");
		
	}
	
	@Command()
	public void edit()
	{
		if(customerDto==null)
		{
			Messagebox.show("Silahkan pilih data");
			
		}
		else {
			Sessions.getCurrent().setAttribute("dto", customerDto);
			Executions.sendRedirect("customer_detail.zul");
		}
		
	}
}