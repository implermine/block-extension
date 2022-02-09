package team.flow.blockextension.dto;

import java.util.List;

public class datapractice {

    private List<Integer> list_integer;
    private Boolean bool;
    private int integerValue;

    public datapractice() {
    }

    public List<Integer> getList_integer() {
        return this.list_integer;
    }

    public Boolean getBool() {
        return this.bool;
    }

    public int getIntegerValue() {
        return this.integerValue;
    }

    public void setList_integer(List<Integer> list_integer) {
        this.list_integer = list_integer;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public void setIntegerValue(int integerValue) {
        this.integerValue = integerValue;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof datapractice)) return false;
        final datapractice other = (datapractice) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$list_integer = this.getList_integer();
        final Object other$list_integer = other.getList_integer();
        if (this$list_integer == null ? other$list_integer != null : !this$list_integer.equals(other$list_integer))
            return false;
        final Object this$bool = this.getBool();
        final Object other$bool = other.getBool();
        if (this$bool == null ? other$bool != null : !this$bool.equals(other$bool)) return false;
        if (this.getIntegerValue() != other.getIntegerValue()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof datapractice;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $list_integer = this.getList_integer();
        result = result * PRIME + ($list_integer == null ? 43 : $list_integer.hashCode());
        final Object $bool = this.getBool();
        result = result * PRIME + ($bool == null ? 43 : $bool.hashCode());
        result = result * PRIME + this.getIntegerValue();
        return result;
    }

    public String toString() {
        return "datapractice(list_integer=" + this.getList_integer() + ", bool=" + this.getBool() + ", integerValue=" + this.getIntegerValue() + ")";
    }
}
