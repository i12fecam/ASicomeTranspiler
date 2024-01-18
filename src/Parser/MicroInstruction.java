package Parser;

public enum MicroInstruction {
    //Mar
    pc_to_mar,
    gpr_ad_to_mar,
    sp_to_mar,
    //OPR
    gpr_to_m,
    gpr_op_to_opr,
    //PC,SP,SC
    pc_plus_to_pc,
    gpr_to_pc,
    sp_plus_to_pc,
    sp_minus_to_pc,
    load_sc,
    sc_minus_to_sc,
    //GPR
    m_to_gpr,
    acc_to_gpr,
    pc_to_gpr,
    gpr_plus_to_gpr,
    qr_to_gpr,
    neg_gpr_to_gpr,
    neg_gpr_plus_to_gpr,
    //ALU
    zero_to_acc,
    neg_acc_to_acc,
    acc_plus_to_acc,
    neg_acc_plus_to_acc,
    gpr_plus_acc_to_acc,
    rol_f_acc,
    ror_f_acc,
    zero_to_qr,
    one_to_ovf,
    zero_to_ovf,
    zero_to_qn_plus,
    neg_qr_plus_to_qr,
    gpr_to_qr,
    m_to_qr,
    one_to_qn,
    x_to_qs,
    ashr_acc_qr,
    rol_f_acc_qr,
    ror_f_acc_qr,
    shl_f_a_q,
    zero_to_f,
    neg_f_to_f,
    neg_gpr_plus_one_plus_acc_to_acc,//alomejor reestructurar
    neg_accqr_plus_to_accqr,//alomejor reestructurar
    zero_to_n,
    one_to_n,
    neg_a_plus_to_a,
    neg_as_to_as,
    zero_to_as,
    as_to_qs,
    qs_xor_bs_to_as,
    qs_xor_bs_to_qs,//revisar si es de verdad xor
    neg_q_plus_to_q,
    zero_to_a,
    a_plus_b_to_ea,
    a_plus_neg_b_plus_one_to_ea,//posible ambiguacion?
    a_plus_neg_b_plus_one_to_a,
    e_to_ovf,

}
