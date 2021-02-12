package br.com.standard.domains.dashboard;

public class DashboardStandardReportDtoBuilder {

    private int advisoryQuantity;
    private int consultancyQuantity;
    private int defaultQuantity;

    public DashboardStandardReportDtoBuilder setAdvisoryQuantity(int advisoryQuantity) {
        this.advisoryQuantity = advisoryQuantity;
        return this;
    }

    public DashboardStandardReportDtoBuilder setConsultancyQuantity(int consultancyQuantity) {
        this.consultancyQuantity = consultancyQuantity;
        return this;
    }

    public DashboardStandardReportDtoBuilder setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
        return this;
    }

    public DashboardStandardReportDto build() {
        return new DashboardStandardReportDto(this.advisoryQuantity,
                this.consultancyQuantity,
                this.defaultQuantity);
    }
}
